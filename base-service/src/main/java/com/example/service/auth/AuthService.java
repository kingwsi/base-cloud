package com.example.service.auth;

import com.example.common.feign.WechatFeignClient;
import com.example.common.pojo.wechat.WechatCode2Session;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * description:  <br>
 * date: 2021/07/01 19:30 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Service
@Slf4j
public class AuthService {

    private final String secret = "903c0054ccf6b60b9fe906b550e557d2";

    @Autowired
    private WechatFeignClient wechatFeignClient;


    public void wechatOauth(String code) {
        String responseStr = wechatFeignClient.code2Session(secret, code);
        ObjectMapper objectMapper = new ObjectMapper();
        WechatCode2Session wechatCode2Session = null;
        try {
            wechatCode2Session = objectMapper.readValue(responseStr, WechatCode2Session.class);
            log.info("wechat user info {}", wechatCode2Session);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
