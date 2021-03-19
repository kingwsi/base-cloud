package com.example.admin.api;

import com.example.common.bean.AuthUser;
import com.example.common.bean.ResponseData;
import com.example.common.enumerate.RedisKey;
import com.example.service.AccessControlService;
import com.wf.captcha.SpecCaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.UUID;

/**
 * Description: 授权相关接口
 * Name: AuthController
 * Author: wangshu
 * Date: 2019/6/29 15:15
 */
@Api(tags = "授权相关接口")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AccessControlService accessControlService;

    private final StringRedisTemplate stringRedisTemplate;

    public AuthController(AccessControlService accessControlService, StringRedisTemplate stringRedisTemplate) {
        this.accessControlService = accessControlService;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    @ApiOperation("获取token")
    @PostMapping
    public ResponseData<String> auth(@RequestBody AuthUser authUser) {
        String key = RedisKey.LOGIN_VERIFY_CODE + authUser.getKey();
        String code = stringRedisTemplate.opsForValue().get(key);
        stringRedisTemplate.delete(key);
        if (!authUser.getVerifyCode().equalsIgnoreCase(code)) {
            return ResponseData.FAIL("验证码错误");
        } else {
            return ResponseData.OK(accessControlService.auth(authUser));
        }
    }

    @ApiOperation("captcha")
    @GetMapping("/captcha")
    public ResponseData<?> captcha() {
        SpecCaptcha specCaptcha = new SpecCaptcha(130, 48, 5);
        String verCode = specCaptcha.text().toLowerCase();
        String key = UUID.randomUUID().toString();
        stringRedisTemplate.opsForValue().set(RedisKey.LOGIN_VERIFY_CODE + key, verCode, RedisKey.LOGIN_VERIFY_CODE.getExpire());
        HashMap<String, String> hashMap = new HashMap<>();
        hashMap.put("key", key);
        hashMap.put("image", specCaptcha.toBase64());
        return ResponseData.OK(hashMap);
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public ResponseData<?> logout() {
        return ResponseData.OK();
    }
}
