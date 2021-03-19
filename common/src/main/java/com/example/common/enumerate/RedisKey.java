package com.example.common.enumerate;

import java.util.concurrent.TimeUnit;

public enum RedisKey {
    GATEWAY_LOG_FILTER("网关日志订阅", "sub:log:gateway_log" , -1),
    LOGIN_VERIFY_CODE("登录请求验证码", "verify:login", 10),
    USER_AUTH_INFO("授权用户信息", "user:auth",10);


    String description;
    String key;
    Long expire;
    TimeUnit timeUnit;

    RedisKey(String description,String key,  long expire) {
        this.description = description;
        this.expire = expire;
        this.key = key;
        this.timeUnit = TimeUnit.MINUTES;
    }

    RedisKey(String description,String key,  long expire, TimeUnit timeUnit) {
        this.description = description;
        this.key = key;
        this.expire = expire;
        this.timeUnit = timeUnit;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getExpire() {
        return this.expire;
    }

    public String getKey() {
        return key;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    @Override
    public String toString() {
        return this.key;
    }
}
