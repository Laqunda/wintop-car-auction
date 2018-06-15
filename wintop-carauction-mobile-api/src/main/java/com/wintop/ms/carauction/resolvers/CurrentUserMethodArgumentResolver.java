package com.wintop.ms.carauction.resolvers;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.util.utils.RedisAppUserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.net.URI;

/**
 * 增加方法注入，将含有CurrentUser注解的方法参数注入当前登录用户
 * @see com.wintop.ms.carauction.core.annotation.CurrentUser
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class CurrentUserMethodArgumentResolver implements HandlerMethodArgumentResolver {

    private RestTemplate restTemplate;
    @Autowired
    private RedisAppUserManager appUserManager;
    CurrentUserMethodArgumentResolver(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是AppUser并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(AppUser.class) &&
                parameter.hasParameterAnnotation(CurrentUser.class)) {
            return true;
        }
        return false;
    }

    /***
     * 针对需要获取当前用户对象的接口，把登陆对象注入到当前接口使用
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        AppUser appUser = null;
        try {
            //取出鉴权时存入的登录用户Id
            Long currentUserId = (Long) webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);

            if (currentUserId != null) {
                appUser = appUserManager.getAppUser(currentUserId+"");
                if (appUser==null){
                    //从用户服务中查询用户详情并返回
                    ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                            RequestEntity
                                    .post(URI.create(Constants.ROOT + "/service/appuser/getuser"))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(currentUserId), JSONObject.class);
                    JSONObject serviceResult = resultResponseEntity.getBody();
                    if (resultResponseEntity.getStatusCode() == HttpStatus.OK && "true".equals(serviceResult.get("success").toString())) {
                        JSONObject userObj = serviceResult.getJSONObject("result");
                        appUser = new AppUser();
                        appUser.setId(userObj.getLong("id"));
                        appUser.setName(userObj.getString("name"));
                        appUser.setUserName(userObj.getString("userName"));
                        appUser.setHeadImg(userObj.getString("headImg"));
                        appUser.setMobile(userObj.getString("mobile"));
                        appUser.setUserNum(userObj.getString("userNum"));
                        appUser.setStatus(userObj.getString("status"));
                        appUser.setUserRankId(userObj.getLong("userLevelId"));
                        appUserManager.saveUser(appUser);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return appUser;
        }
    }
}