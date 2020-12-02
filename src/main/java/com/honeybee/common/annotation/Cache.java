package com.honeybee.common.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 支持用在方法上，对需要添加缓存的方法做代理，
 * 实现在方法执行前先去查询缓存，如果缓存查到则直接返回，否则在查询DB，更新缓存
 *
 * @author HXY
 * @date 2020-12-3
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Cache {

    /**
     * 缓存key前缀
     */
    String prefix();

    /**
     * 缓存key
     */
    String key();

    /**
     * 过期时间 默认60秒
     */
    int expireTime() default 60;
}
