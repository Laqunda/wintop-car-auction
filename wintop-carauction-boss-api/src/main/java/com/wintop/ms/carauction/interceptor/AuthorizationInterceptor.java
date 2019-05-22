package com.wintop.ms.carauction.interceptor;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.AuthPublic;
import com.wintop.ms.carauction.core.annotation.AuthUserToken;
import com.wintop.ms.carauction.core.annotation.RequestAuth;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.config.HttpResponseStatus;
import com.wintop.ms.carauction.core.config.ManagerRole;
import com.wintop.ms.carauction.core.config.ResultCode;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.core.entity.TokenManager;
import com.wintop.ms.carauction.entity.ManagerRolePages;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
import com.wintop.ms.carauction.core.model.ResultModel;
import com.wintop.ms.carauction.core.model.TokenModel;
import com.wintop.ms.carauction.util.utils.RedisTokenManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.List;

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

    private static final Logger logger = LoggerFactory.getLogger(AuthorizationInterceptor.class);

    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws IOException{
        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, DELETE, PUT, PATCH");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "Origin, No-Cache, X-Requested-With, If-Modified-Since, Pragma, Last-Modified, Cache-Control, Expires, Content-Type, X-E4M-With,token,appId,timestamp,authorization");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("XDomainRequestAllowed","1");
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
        //从header中得到appId
        String appId = request.getHeader(Constants.HEADER_APPID);
        //从header中得到token
        String authorization = request.getHeader(Constants.HEADER_AUTHORIZATION);
        //判断是否传递了必须参数
        if (StringUtils.isEmpty(appId)) {
            ResultModel resultModel =
                    new ResultModel(false,ResultCode.APPID_NULL.value(),ResultCode.APPID_NULL.getRemark(),null);
            String obj = JSONObject.toJSONString(resultModel);
            pw.print(obj);
            pw.flush();
            pw.close();
            return false;
        }else if (!manager.appIfAuth(appId)){
            //203 appId未经授权

            ResultModel resultModel =
                    new ResultModel(false,ResultCode.APPID_DISABLED.value(),ResultCode.APPID_DISABLED.getRemark(),null);
            String obj = JSONObject.toJSONString(resultModel);
            pw.print(obj);
            pw.flush();
            pw.close();
            return false;
        }else if (method.getAnnotation(AuthUserToken.class) != null){/**当接口方法用到了AuthUserToken注解时需要验证token*/
                //判断参数是否满足
               if (StringUtils.isEmpty(authorization) || authorization.indexOf("_")==-1){
                   ResultModel result =
                           new ResultModel(false,ResultCode.TOKEN_NULL.value(),ResultCode.TOKEN_NULL.getRemark(),null);
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
                       request.setAttribute(Constants.CURRENT_USER_ID, model.getUserId());
                       //TokenSingleton.getInstance().setTokenModel(model);
                       //**验证成功后判断访问权限
                       boolean result = false;
                       RequestAuth auth = handlerMethod.getMethodAnnotation(RequestAuth.class);
                       //System.out.println(auth);
                       if(auth==null || auth.value()){
                           List<String> pageUrls = ManagerRolePages.getInstance().getAllRequestPages(model.getUserId());
                           for(String str:pageUrls){
                               if(url.contains(str)){
                                   result=true;
                                   break;
                               }
                           }
  /*                         if(!result){
                               ResultModel resultModel =
                                       new ResultModel(false,ResultCode.NO_REQUEST_AUTH.value(),ResultCode.NO_REQUEST_AUTH.getRemark(),null);
                               String obj = JSONObject.toJSONString(resultModel);
                               pw.print(obj);
                               pw.flush();
                               pw.close();
                               return result;
                           }*/
                           return true;
                       }else{
                           result = true;
                       }
                       return result;
                   }else {
                       ResultModel result =
                               new ResultModel(false,ResultCode.TOKEN_DISABLED.value(),ResultCode.TOKEN_DISABLED.getRemark(),null);
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