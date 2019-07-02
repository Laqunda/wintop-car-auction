package com.wintop.ms.carauction.resolvers;

import com.alibaba.fastjson.JSONObject;
import com.wintop.ms.carauction.core.annotation.CurrentManagerUser;
import com.wintop.ms.carauction.core.annotation.CurrentUser;
import com.wintop.ms.carauction.core.config.Constants;
import com.wintop.ms.carauction.core.entity.AppUser;
import com.wintop.ms.carauction.core.entity.CarManagerUser;
import com.wintop.ms.carauction.util.utils.RedisManagerTemplate;
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
 * 增加方法注入，将含有CurrentManagerUser注解的方法参数注入当前登录用户
 * @see CurrentManagerUser
 */
@Component
public class CurrentManagerUserMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private int port = 8185;

    private RestTemplate restTemplate;
    @Autowired
    private RedisManagerTemplate redisManagerTemplate;
    private CarManagerUser carManagerUser;

    CurrentManagerUserMethodArgumentResolver(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果参数类型是AppUser并且有CurrentUser注解则支持
        if (parameter.getParameterType().isAssignableFrom(CarManagerUser.class) &&
                parameter.hasParameterAnnotation(CurrentManagerUser.class)) {
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
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) {
        Object result = null;
        try {
            //取出鉴权时存入的登录用户Id
            Long currentUserId = (Long) webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
            String key = Constants.CAR_MANAGER_USER_REDIS_KEY + "_" + currentUserId;
            Object redisManagerUser = redisManagerTemplate.get(key);
            if (redisManagerUser != null) {
                result = JSONObject.parseObject(redisManagerUser.toString(),CarManagerUser.class);
            } else {
                if (currentUserId != null) {
                    //从用户服务中查询用户详情并返回
                    ResponseEntity<JSONObject> resultResponseEntity = this.restTemplate.exchange(
                            RequestEntity
                                    .post(URI.create(Constants.ROOT + "/service/managerUser/getById"))
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .body(currentUserId), JSONObject.class);
                    JSONObject serviceResult = resultResponseEntity.getBody();
                    if (resultResponseEntity.getStatusCode() == HttpStatus.OK && "true".equals(serviceResult.get("success").toString())) {
                        JSONObject userObj = serviceResult.getJSONObject("result");
                        carManagerUser = new CarManagerUser();
                        carManagerUser.setId(userObj.getLong("id"));
                        carManagerUser.setUserName(userObj.getString("userName"));
                        carManagerUser.setUserKey(userObj.getString("userKey"));
                        carManagerUser.setUserPassword(userObj.getString("userPassword"));
                        carManagerUser.setUserCode(userObj.getString("userCode"));
                        carManagerUser.setUserPhoto(userObj.getString("userPhoto"));
                        carManagerUser.setRoleId(userObj.getLong("roleId"));
                        carManagerUser.setRoleTypeId(userObj.getLong("roleTypeId"));
                        carManagerUser.setDepartmentId(userObj.getLong("departmentId"));
                        carManagerUser.setRegionId(userObj.getLong("regionId"));
                        result = carManagerUser;
                        redisManagerTemplate.add(key,JSONObject.toJSONString(carManagerUser));
                    } else {
                        result = null;
                    }
                }
                throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            return result;
        }
    }
}