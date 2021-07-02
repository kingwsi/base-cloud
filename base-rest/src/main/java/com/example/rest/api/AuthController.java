package com.example.rest.api;

import com.example.common.bean.ResponseData;
import com.example.service.auth.AuthService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: 授权登陆 <br>
 * date: 2021/7/01 19:14 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "member")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/wechat")
    public ResponseData<?> wechatAuth(String code){
        return ResponseData.OK(authService.wechatOauth(code));
    }

    @PostMapping("/info")
    public ResponseData<?> bindInfo(String code){
        return ResponseData.OK(authService.wechatOauth(code));
    }
}
