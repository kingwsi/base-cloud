package com.example.common.enumerate;

import java.util.concurrent.TimeUnit;

public enum RedisConstKey {
    GATEWAY_LOG_FILTER("网关日志订阅", "sub:log:gateway_log" , -1),
    LOGIN_VERIFY_CODE("登录请求验证码", "verify:login", 3),
    USER_AUTH_INFO("授权用户信息", "user:auth",10),
    WECHAT_TEMP_AUTH("微信授权临时信息", "wechat_auth:tmp_code", 60)

    ;


    private final String description;
    private final String key;
    private final Long expire;
    private final TimeUnit timeUnit;

    RedisConstKey(String description, String key, long expire) {
        this.description = description;
        this.expire = expire;
        this.key = key;
        this.timeUnit = TimeUnit.MINUTES;
    }

    RedisConstKey(String description, String key, long expire, TimeUnit timeUnit) {
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
