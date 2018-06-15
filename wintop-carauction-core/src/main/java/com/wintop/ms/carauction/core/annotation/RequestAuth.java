package com.wintop.ms.carauction.core.annotation;

import java.lang.annotation.*;

/**
 * 访问接口权限
 * 不配置或配置true需要验证权限
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RequestAuth {
    boolean value() default true;
}
