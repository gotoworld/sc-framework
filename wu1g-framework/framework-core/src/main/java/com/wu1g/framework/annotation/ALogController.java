package com.wu1g.framework.annotation;

import java.lang.annotation.*;

/**
 *自定义注解 拦截Controller
 */

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ALogController {
    String type() default  "";
    String desc()  default "";
}
