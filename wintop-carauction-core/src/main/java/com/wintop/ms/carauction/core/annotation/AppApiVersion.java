package com.wintop.ms.carauction.core.annotation;

import java.lang.annotation.*;

/**
 * app接口版本
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface AppApiVersion {
    String value();
}
