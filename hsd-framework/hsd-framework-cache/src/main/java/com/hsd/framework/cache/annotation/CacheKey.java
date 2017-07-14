package com.hsd.framework.cache.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 标记key字段，并可指定索引序号
 */
@Target({ ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface CacheKey {

    /**
     * 指定key的索引序号，-1表示未指定（默认按反射字段数组顺序）
     * @return
     */
    int order() default -1;

}
