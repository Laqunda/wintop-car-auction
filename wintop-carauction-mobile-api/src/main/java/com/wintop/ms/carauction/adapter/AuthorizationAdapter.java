package com.wintop.ms.carauction.adapter;

import com.wintop.ms.carauction.interceptor.AuthorizationInterceptor;
import com.wintop.ms.carauction.resolvers.CurrentPossibleUserIdMethodArgumentResolver;
import com.wintop.ms.carauction.resolvers.CurrentUserIdMethodArgumentResolver;
import com.wintop.ms.carauction.resolvers.CurrentUserMethodArgumentResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.util.List;

/**
 * 配置类，增加自定义拦截器
 * @see AuthorizationAdapter
 * @author ScienJus
 * @date 2015/7/30.
 */
@Configuration
public class AuthorizationAdapter extends WebMvcConfigurerAdapter {

    @Autowired
    private AuthorizationInterceptor authorizationInterceptor;
    @Autowired
    private CurrentUserMethodArgumentResolver currentUserMethodArgumentResolver;
    @Autowired
    private CurrentUserIdMethodArgumentResolver currentUserIdMethodArgumentResolver;
    @Autowired
    private CurrentPossibleUserIdMethodArgumentResolver currentPossibleUserIdMethodArgumentResolver;
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(authorizationInterceptor);
    }
    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(currentUserMethodArgumentResolver);
        argumentResolvers.add(currentUserIdMethodArgumentResolver);
        argumentResolvers.add(currentPossibleUserIdMethodArgumentResolver);
    }
}
