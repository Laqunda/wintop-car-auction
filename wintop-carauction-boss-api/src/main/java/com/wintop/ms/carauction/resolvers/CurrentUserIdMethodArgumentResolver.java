package com.wintop.ms.carauction.resolvers;

import com.wintop.ms.carauction.core.annotation.CurrentUserId;
import com.wintop.ms.carauction.core.config.Constants;
import org.springframework.core.MethodParameter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

/**
 * 增加方法注入，将含有CurrentUserID注解的方法参数注入当前登录用户
 * @see CurrentUserId
 * @author ScienJus
 * @date 2015/7/31.
 */
@Component
public class CurrentUserIdMethodArgumentResolver implements HandlerMethodArgumentResolver {

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        //如果有 CurrentUserId注解 并且类型是Long则是注入用户ID
        if (parameter.getParameterType().isAssignableFrom(Long.class) &&
                parameter.hasParameterAnnotation(CurrentUserId.class)) {
            return true;
        }
        return false;
    }

    /***
     * 针对需要获取当前用户对象ID的接口，把登陆对象ID注入到当前接口使用
     * @param parameter
     * @param mavContainer
     * @param webRequest
     * @param binderFactory
     * @return
     * @throws Exception
     */
    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        //取出鉴权时存入的登录用户Id
        Long currentUserId = (Long) webRequest.getAttribute(Constants.CURRENT_USER_ID, RequestAttributes.SCOPE_REQUEST);
        if (currentUserId != null) {
           return currentUserId;
        }
        throw new MissingServletRequestPartException(Constants.CURRENT_USER_ID);
    }
}