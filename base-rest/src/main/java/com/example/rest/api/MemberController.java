package com.example.rest.api;

import com.example.common.annotation.Limiter;
import com.example.common.bean.AuthUser;
import com.example.common.bean.ResponseData;
import com.example.common.enumerate.LimiterType;
import com.example.service.MemberService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description:  <br>
 * date: 2021/3/11 15:14 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "member")
@RestController
@RequestMapping("/api/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @PostMapping("/register")
    public ResponseData<?> register(@RequestBody AuthUser authUser) {
        memberService.register(authUser);
        return ResponseData.OK();
    }

    @PostMapping("/auth")
    public ResponseData<String> authByPwd(@RequestBody AuthUser authUser) {
        switch (authUser.getAuthType()){
            case PASSWORD:
                return ResponseData.OK(memberService.authByPwd(authUser));
            case MOBILE:
                return ResponseData.OK(memberService.authByMobile(authUser));
            default:
                return ResponseData.FAIL();
        }
    }

    @Limiter(type = LimiterType.IP, requestedTokens = 30, burstCapacity = 30)
    @GetMapping("/limiterTest")
    public ResponseData<?> limiterTest() {
        return ResponseData.OK();
    }

    public void authByMobile() {

    }

}
