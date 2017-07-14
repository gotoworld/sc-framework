package com.hsd.framework.cache.annotation;

import java.lang.annotation.*;

/**
 */
@Target({ ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheSet {

    /**
     * cluster配置名，当只有一个配置时，可不设置
     * @return
     */
    String cluster() default "";

    /**
     * key 前缀
     * @return
     */
    String prefix() default "";

    /**
     * 指定key的字段名，可以多字段组合，按指定的顺序
     * @return
     */
    String[] keys() default {};

}
