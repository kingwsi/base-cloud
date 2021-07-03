package com.example.common.pojo.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * description: TODO <br>
 * date: 2021/07/02 17:16 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WechatAuth {
    private String token;
    private int code;
    private String encryptedData;
    private String iv;

    public WechatAuth(String token, int code) {
        this.token = token;
        this.code = code;
    }
}
