package com.example.admin.api;

import com.example.common.bean.AuthUser;
import com.example.common.bean.ResponseData;
import com.example.common.enumerate.RedisConstKey;
import com.example.service.AccessControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("获取token(验证验证码)")
    @PostMapping("/verify")
    public ResponseData<String> authWithVerify(@RequestBody AuthUser authUser) {
        String key = RedisConstKey.LOGIN_VERIFY_CODE + authUser.getKey();
        String code = stringRedisTemplate.opsForValue().get(key);
        stringRedisTemplate.delete(key);
        if (StringUtils.isEmpty(code) || !code.equalsIgnoreCase(authUser.getCaptcha())) {
            return ResponseData.FAIL("验证码错误");
        } else {
            return ResponseData.OK(accessControlService.auth(authUser));
        }
    }

    @ApiOperation("获取token")
    @PostMapping
    public ResponseData<String> auth(@RequestBody AuthUser authUser) {
        return ResponseData.OK(accessControlService.auth(authUser));
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public ResponseData<?> logout() {
        return ResponseData.OK();
    }
}
