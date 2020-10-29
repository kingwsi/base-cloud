package com.example.admin.api;

import com.example.common.bean.AuthUser;
import com.example.common.bean.ResponseData;
import com.example.service.AccessControlService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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

    public AuthController(AccessControlService accessControlService) {
        this.accessControlService = accessControlService;
    }

    @ApiOperation("获取令牌")
    @PostMapping
    public ResponseData createToken(@RequestBody AuthUser authUser) {
        return ResponseData.OK(accessControlService.auth(authUser));
    }

    @ApiOperation("登出")
    @PostMapping("/logout")
    public ResponseData logout() {
        return ResponseData.OK();
    }
}
