package com.example.common.exception;

import com.example.common.bean.ResponseData;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Description: 全局异常处理
 * Name: GlobalExceptionHandler
 * Author: wangshu
 * Date: 2019/7/27 1:25
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ResponseData<?> handlerException(Exception e) {
        log.error(e.getMessage());
        e.printStackTrace();
        return ResponseData.FAIL("服务器异常", 500);
    }

    @ExceptionHandler(value = CustomException.class)
    public ResponseData<?> handlerCustomException(CustomException ex) {
        log.warn(ex.getMessage());
        return ResponseData.FAIL(ex.getMessage(), ex.getStatus());
    }

    /**
     * Valid 校验异常
     *
     * @return
     * @throws MethodArgumentNotValidException
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseData<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        FieldError fieldError = ex.getBindingResult().getFieldError();
        assert fieldError != null;
        return ResponseData.FAIL(fieldError.getDefaultMessage());
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseData<?> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException ex) {
        log.error("不支持 {}",ex.getMethod());
        return ResponseData.FAIL(ex.getMessage());
    }
}
