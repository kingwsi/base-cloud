package com.example.common.pojo.wechat;

import lombok.Data;

@Data
public class AccessToken {

    /**
     * 获取到的凭证
     */
    private String access_token;

    /**
     * 凭证有效时间，单位：秒。目前是7200秒之内的值。
     */
    private long expires_in;
    private long errcode;
    private String errmsg;
}
