package com.example.rest.api;

import com.example.common.bean.ResponseData;
import com.example.common.pojo.wechat.WechatAuth;
import com.example.service.auth.AuthService;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.*;

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
    public ResponseData<?> wechatAuth(String code) throws Exception {
        return ResponseData.OK(authService.wechatOauth(code));
    }

    @PostMapping("/wechat/info")
    public ResponseData<?> bindInfo(@RequestBody WechatAuth wechatAuth){
        return ResponseData.OK(authService.bindWechatInfo(wechatAuth));
    }

    @GetMapping("/info")
    public ResponseData<?> getMemberInfo(){
        return ResponseData.OK(authService.getCurrentMemberInfo());
    }
}
