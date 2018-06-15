package com.wintop.ms.carauction.core.annotation;

import com.wintop.ms.carauction.core.config.AppUserStatusEnum;

import java.lang.annotation.*;

/**
 * 买家访问接口权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppUserRequestAuth {
    AppUserStatusEnum[] value();
}
