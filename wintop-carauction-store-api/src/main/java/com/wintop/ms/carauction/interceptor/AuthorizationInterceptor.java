package com.wintop.ms.carauction.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AppApiVersion;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.HttpResponseStatus;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.core.entity.TokenSingleton;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.RedisStoreUserManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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
 * @see AuthPublic
 * @see AuthUserToken
 * @author liangts
 * @date
 */
@Component
public class AuthorizationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private TokenManager manager;
    @Autowired
    private RedisStoreUserManager storeUserManager;

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException{
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;charset=UTF-8");
        OutputStream out = response.getOutputStream();
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(out,"UTF-8"));
        String url=request.getRequestURI();
        //判断是否是swagger的api文档请求
        if(url.indexOf("swagger")!=-1||url.indexOf("api-docs")!=-1){
            return true;
        }
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        /**加入Authpublic公开接口注解的方法不用拦截直接通过*/
        if (method.getAnnotation(AuthPublic.class)!=null){
            return true;
        }
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

        //从header中得到appId
        String appId = request.getHeader(Constants.HEADER_APPID);
        //从header中得到token
        String authorization = request.getHeader(Constants.HEADER_AUTHORIZATION);
        //判断是否传递了必须参数
        if (StringUtils.isEmpty(appId)) {
            ResultModel resultModel =
                    new ResultModel(false,HttpResponseStatus.APPID_NULL,"缺少appId，不能使用接口！");
            String obj = JSONObject.toJSONString(resultModel);
            pw.print(obj);
            pw.flush();
            pw.close();
            return false;
        }else if (!manager.appIfAuth(appId)){
            //203 appId未经授权

            ResultModel resultModel =
                    new ResultModel(false,HttpResponseStatus.APPID_DISABLED,"应用未经授权，不能使用接口！");
            String obj = JSONObject.toJSONString(resultModel);
            pw.print(obj);
            pw.flush();
            pw.close();
            return false;
        }else if (method.getAnnotation(AuthUserToken.class) != null){/**当接口方法用到了AuthUserToken注解时需要验证token*/
                //判断参数是否满足
               if (StringUtils.isEmpty(authorization) || authorization.indexOf("_")==-1){
                   ResultModel result =
                           new ResultModel(false,HttpResponseStatus.TOKEN_NULL,"缺少token授权，不能使用接口！");
                   String obj = JSONObject.toJSONString(result);
                   pw.print(obj);
                   pw.flush();
                   pw.close();
                   return false;
               }else {
                   //验证token
                   TokenModel model = manager.getToken(appId, authorization);
                   if (manager.checkToken(model)) {
                       //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                       //request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
                       TokenSingleton.getInstance().setTokenModel(model);
                       //**验证成功后判断访问权限
                       boolean result = false;
                       CarManagerUser user = storeUserManager.getStoreUser(model.getUserId()+"");

                       if(user==null){
                           ResultModel resultModel =
                                   new ResultModel(false,HttpResponseStatus.TOKEN_DISABLED,"无效的token授权，不能使用接口！",null);
                           String obj = JSONObject.toJSONString(resultModel);
                           pw.print(obj);
                           pw.flush();
                           pw.close();
                           return result;
                       }
//                       RequestAuth auth = handlerMethod.getMethodAnnotation(RequestAuth.class);
//                       System.out.println(auth);
//                       if(auth!=null && auth.value().length>0){
//                           ManagerRole[] roles = auth.value();
//                           for(ManagerRole role:roles){
//                               System.out.println(role.value());
//                               if(user.getRoleId()==role.value()){
//                                   result = true;
//                                   break;
//                               }
//                           }
//                           if(!result){
//                               ResultModel resultModel =
//                                       new ResultModel(false,211,"无权限请求接口");
//                               String obj = JSONObject.toJSONString(resultModel);
//                               pw.print(obj);
//                               pw.flush();
//                               pw.close();
//                               return result;
//                           }
//                       }else{
//
//                           //如果token验证成功，将token对应的用户id存在request中，便于之后注入
                           request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
                           result = true;
//                       }
                       return result;
                   }else {
                       ResultModel result =
                               new ResultModel(false,HttpResponseStatus.TOKEN_DISABLED,"无效的token授权，不能使用接口！");
                       String obj = JSONObject.toJSONString(result);
                       pw.print(obj);
                       pw.flush();
                       pw.close();
                       return false;
                   }
               }
        }
        return true;
    }
}