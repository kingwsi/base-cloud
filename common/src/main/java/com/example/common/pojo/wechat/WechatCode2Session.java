package com.example.common.pojo.wechat;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class WechatCode2Session {
    private String openid;

    @JsonProperty(value = "session_key")
    private String sessionKey;

    private String unionid;

    private int errcode;

    private String errmsg;
}
