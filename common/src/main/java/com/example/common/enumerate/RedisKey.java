package com.example.common.enumerate;

public enum RedisKey {
    GATEWAY_LOG_FILTER("网关日志订阅", -1),
    USER_AUTH_INFO("授权用户信息", 10);
    String description;
    Long expire;

    RedisKey(String description, long expire) {
        this.description = description;
        this.expire = expire;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getExpire() {
        return this.expire;
    }
}
