package com.wintop.ms.carauction.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AppUserRequestAuth;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.AppUserStatusEnum;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.HttpResponseStatus;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.RedisAppUserManager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * 自定义拦截器，判断此次请求是否有权限【1.默认应用授权拦截，2登陆授权，3公开接口跳过所有拦截】
 * 通过appid和token拦截
 *
 * @author liangts
 * @date
 * @see com.wintop.ms.carauction.core.annotation.AuthPublic
 * @see com.wintop.ms.carauction.core.annotation.AuthUserToken
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;
    @Autowired
    private RedisAppUserManager appUserManager;

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException {
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out, "UTF-8"));
        String url = request.getRequestURI();
        //判断是否是swagger的api文档请求
        if (url.indexOf("swagger") != -1 || url.indexOf("api-docs") != -1) {
            return true;
        }
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        /**加入Authpublic公开接口注解的方法不用拦截直接通过*/
        if (method.getAnnotation(AuthPublic.class) != null) {
            return true;
        }
        //从header中得到appId
        String appId = request.getHeader(Constants.HEADER_APPID);
        //从header中得到token
        String authorization = request.getHeader(Constants.HEADER_AUTHORIZATION);
        //判断是否传递了必须参数==201
        authorization = "5_5c3231d8f6fa4603875d995490e9bccb";
        if (method.getAnnotation(AppApiVersion.class) != null) {/**当接口方法用到了AppApiVersion注解时需要验证version*/
            //从header中得到version
            String v = request.getHeader(Constants.APP_VERSION);
            AppApiVersion version = handlerMethod.getMethodAnnotation(AppApiVersion.class);
            if (version.value() != null && !version.value().equals(v)) {
                ResultModel resultModel =
                        new ResultModel(false, HttpResponseStatus.API_VERSION_MISMATCH, "接口版本不匹配,接口版本："+version.value(), null);
                String obj = JSONObject.toJSONString(resultModel);
                pw.print(obj);
                pw.flush();
                pw.close();
                return false;
            }
        }


        if (StringUtils.isEmpty(appId)) {
            ResultModel resultModel =
                    new ResultModel(false, HttpResponseStatus.APPID_NULL, "缺少appId，不能使用接口！", null);
            String obj = JSONObject.toJSONString(resultModel);
            pw.print(obj);
            pw.flush();
            pw.close();
            return false;
        } else if (!manager.appIfAuth(appId)) {
            //203 appId未经授权

            ResultModel resultModel =
                    new ResultModel(false, HttpResponseStatus.APPID_DISABLED, "应用未经授权，不能使用接口！", null);
            String obj = JSONObject.toJSONString(resultModel);
            pw.print(obj);
            pw.flush();
            pw.close();
            return false;
        } else if (method.getAnnotation(AuthUserToken.class) != null) {/**当接口方法用到了AuthUserToken注解时需要验证token*/
            //判断参数是否满足
            if (StringUtils.isEmpty(authorization) || authorization.indexOf("_") == -1) {
                ResultModel result =
                        new ResultModel(false, HttpResponseStatus.TOKEN_NULL, "缺少token授权，不能使用接口！", null);
                String obj = JSONObject.toJSONString(result);
                pw.print(obj);
                pw.flush();
                pw.close();
                return false;
            } else {
                //验证token
                TokenModel model = manager.getToken(appId, authorization);
                if (manager.checkToken(model)) {
                    //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                    request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
                    //TokenSingleton.getInstance().setTokenModel(model);
                    //**验证成功后判断访问权限
                    boolean result = true;
                    AppUser user = appUserManager.getAppUser(model.getUserId() + "");
                    if (user == null) {
                        ResultModel resultModel =
                                new ResultModel(false, HttpResponseStatus.TOKEN_DISABLED, "无效的token授权，不能使用接口！", null);
                        String obj = JSONObject.toJSONString(resultModel);
                        pw.print(obj);
                        pw.flush();
                        pw.close();
                        return result;
                    }
                    //拦截带有AppUserRequestAuth 注解的方法，并验证当前用户状态 是否
                    AppUserRequestAuth auth = handlerMethod.getMethodAnnotation(AppUserRequestAuth.class);
                    if (auth != null && auth.value().length > 0) {
                        AppUserStatusEnum[] roles = auth.value();
                        for (AppUserStatusEnum role : roles) {
                            if (role.value().equals(user.getStatus())) {
                                result = true;
                                break;
                            }
                        }
                        if (!result) {
                            ResultCode resultCode;
                            //需要根据用户状态返回对应的状态码，
                            //    需要实名认证====1===210
                            //    需要签约====3、6===212
                            //    需缴纳保证金====4===213
                            //     签合约审核中====5===214
                            if ("1".equals(user.getStatus())) {
                                resultCode = ResultCode.NOT_VERIFY_REAL_NAME;
                            } else if ("3".equals(user.getStatus()) || "6".equals(user.getStatus())) {
                                resultCode = ResultCode.DID_NOT_SIGN_UP;
                            } else if ("4".equals(user.getStatus())) {
                                resultCode = ResultCode.UNSECURED_DEPOSIT;
                            } else if ("5".equals(user.getStatus())) {
                                resultCode = ResultCode.SIG_AUTH_ING;
                            } else {
                                resultCode = ResultCode.NO_REQUEST_AUTH;
                            }
                            ResultModel resultModel =
                                    new ResultModel(false, resultCode.value(), resultCode.getRemark(), null);
                            String obj = JSONObject.toJSONString(resultModel);
                            pw.print(obj);
                            pw.flush();
                            pw.close();
                            return result;
                        }
                    }
                    return result;
                } else {
                    ResultModel result =
                            new ResultModel(false, HttpResponseStatus.TOKEN_DISABLED, "无效的token授权，不能使用接口！", null);
                    String obj = JSONObject.toJSONString(result);
                    pw.print(obj);
                    pw.flush();
                    pw.close();
                    return false;
                }
            }
        } else if (StringUtils.isNotEmpty(authorization) && authorization.indexOf("_") > -1) {
            TokenModel model = manager.getToken(appId, authorization);
            if (manager.checkToken(model)) {
                //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
            }
        }
        return true;
    }
}