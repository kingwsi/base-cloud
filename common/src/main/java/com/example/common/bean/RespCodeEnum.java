package com.example.common.bean;

import lombok.Getter;

/**
 * description: AuthCode <br>
 * date: 2020/9/30 13:40 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Getter
public enum RespCodeEnum {

    NOT_FOUND("404",404, "找不到页面"),
    // 参数不符合预期，可能是非法调用
    FORBIDDEN("403", "Forbidden"),




    USER_DISABLE("当前用户已禁用"),
    AUTH_FAILED("无效的账号或密码"),
    MEMBER_UNBIND("510", "临时token失效"),
    NOT_FOUND_RECORD("未找到该条记录"),
    ;

    private String code;

    private int httpStatus;

    private String description;

    RespCodeEnum(String description) {
        this.code = "500";
        this.httpStatus = 500;
        this.description = description;
    }

    RespCodeEnum(String code, String description) {
        this.code = code;
        this.description = description;
    }

    RespCodeEnum(String code, int httpStatus, String description) {
        this.code = code;
        this.description = description;
        this.httpStatus = httpStatus;
    }
}
