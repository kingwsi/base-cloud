package com.example.admin.api;

import com.example.common.bean.ResponseData;
import com.example.common.enumerate.RedisConstKey;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.UUID;

/**
 * Description: 授权相关接口
 * Name: AuthController
 * Author: wangshu
 * Date: 2021/03/23 15:15
 */
@Api(tags = "授权相关接口")
@RestController
@RequestMapping("/api/verification")
public class VerificationController {

    private final StringRedisTemplate stringRedisTemplate;

    public VerificationController(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @ApiOperation("验证码(Base64)")
    @GetMapping("/captcha")
    public ResponseData<?> captcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(100, 45, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(RedisConstKey.LOGIN_VERIFY_CODE + key, verCode, RedisConstKey.LOGIN_VERIFY_CODE.getExpire(), RedisConstKey.LOGIN_VERIFY_CODE.getTimeUnit());
        String val = stringRedisTemplate.opsForValue().get(RedisConstKey.LOGIN_VERIFY_CODE + key);
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", key);
        hashMap.put("image", specCaptcha.toBase64());
        return ResponseData.OK(hashMap);
    }
}
