package com.example.common.annotation;

import com.example.common.enumerate.LimiterType;

import java.lang.annotation.*;

/**
 * description: Limiter <br>
 * date: 2021/3/12 13:22 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface Limiter {

    /**
     * 类型
     * PATH 请求地址
     * USER 用户
     * IP IP地址
     *
     * @return
     */
    LimiterType type() default LimiterType.PATH;

    /**
     * 每秒生产令牌数量
     *
     * @return
     */
    int replenishRate() default 1;

    /**
     * 桶容量 即每秒最大并发数量
     *
     * @return
     */
    int burstCapacity() default 2;

    /**
     * 每次请求消耗令牌数量
     * @return
     */
    int requestedTokens() default 1;
}
