package com.example.gateway.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 同意返回对象封装
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public ResponseData<T> OK() {
        return new ResponseData<T>(200, "OK");
    }

    public static <T> ResponseData<T> OK(T data) {
        return new ResponseData<T>(200, "OK", data);
    }

    public ResponseData<T> FAIL() {
        return new ResponseData<T>(500, "服务器错误");
    }

    public ResponseData<T> FAIL(String msg) {
        return new ResponseData<T>(500, msg);
    }

    public ResponseData<T> FAIL(String msg, Integer code) {
        return new ResponseData<T>(code, msg);
    }
}
