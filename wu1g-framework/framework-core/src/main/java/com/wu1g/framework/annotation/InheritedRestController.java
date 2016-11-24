package com.wu1g.framework.annotation;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * Created by Administrator on 16-9-10.
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@RestController
public @interface InheritedRestController {
    String value() default "";
}
