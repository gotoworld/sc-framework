package com.wu1g.framework.annotation;

import java.lang.annotation.*;

/**
 *自定义注解 拦截service
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ALogService {
    String desc()  default "";
}
