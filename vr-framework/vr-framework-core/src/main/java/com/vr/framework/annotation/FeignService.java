package com.vr.framework.annotation;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;

import java.lang.annotation.*;

/**
 * 用于Service实现类注解，主要目的：当使用独立服务部署或“云部署”时，设置使用@Service，还是@RestController
 * 当使用云部署时，修改@Service为@RestController
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
//@Inherited
//@Service
//@RestController
public @interface FeignService {
    String value() default "";
}
