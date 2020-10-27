package com.example.common.exception;

import com.example.common.bean.RespCodeEnum;
import org.apache.http.HttpStatus;

/**
 * Description: 自定义异常封装
 * Name: CustomException
 * Author: wangshu
 * Date: 2019/7/27 1:24
 */

public class CustomException extends RuntimeException {
    private int status;

    private String code;

    public CustomException(RespCodeEnum codeEnum) {
        //父类的构造方法本身会传message进去
        super(codeEnum.getDescription());
        this.status = codeEnum.getHttpStatus();
    }

    public CustomException(String message) {
        //父类的构造方法本身会传message进去
        super(message);
        this.status = HttpStatus.SC_INTERNAL_SERVER_ERROR;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
