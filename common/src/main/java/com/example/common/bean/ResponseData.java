package com.example.common.bean;

import java.io.Serializable;

/**
 * 返回对象封装
 */
public class ResponseData<T> implements Serializable {
    private Integer code;
    private String message;
    private T data;

    public ResponseData(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public static <T> ResponseData<T> OK() {
        return new ResponseData<>(200, "OK");
    }

    public static <T> ResponseData<T> OK(T data) {
        return new ResponseData<>(200, "OK", data);
    }

    public static <T> ResponseData<T> FAIL() {
        return new ResponseData<>(500, "服务器错误");
    }

    public static <T> ResponseData<T> FAIL(String msg) {
        return new ResponseData<>(500, msg);
    }

    public static <T> ResponseData<T> FAIL(String msg, Integer code) {
        return new ResponseData<>(code, msg);
    }

    public Integer getCode() {
        return code;
    }

    public ResponseData<T> setCode(Integer code) {
        this.code = code;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public ResponseData<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public T getData() {
        return data;
    }

    public ResponseData<T>  setData(T data) {
        this.data = data;
        return this;
    }

    public ResponseData(Integer code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ResponseData() {
    }
}
