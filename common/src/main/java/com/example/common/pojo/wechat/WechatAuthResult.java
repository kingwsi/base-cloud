package com.example.common.pojo.wechat;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * description: TODO <br>
 * date: 2021/07/02 17:16 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Data
@AllArgsConstructor
public class WechatAuthResult {
    private String token;
    private int code;
}
