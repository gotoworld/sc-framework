package com.wu1g.framework.annotation;

import org.springframework.stereotype.Controller;

import java.lang.annotation.*;

/**
 * Created by Administrator on 16-9-10.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Controller
public @interface InheritedController {
    String value() default "";
}
