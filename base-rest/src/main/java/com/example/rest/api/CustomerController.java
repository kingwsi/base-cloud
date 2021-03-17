package com.example.rest.api;

import com.example.common.annotation.Limiter;
import com.example.common.bean.AuthUser;
import com.example.common.bean.ResponseData;
import com.example.common.enumerate.LimiterType;
import com.example.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * description: UserController <br>
 * date: 2021/3/11 15:14 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Api(tags = "customer")
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/register")
    public ResponseData<?> register(@RequestBody AuthUser authUser) {
        customerService.register(authUser);
        return ResponseData.OK();
    }

    @PostMapping("/authByPwd")
    public ResponseData<String> authByPwd(@RequestBody AuthUser authUser) {
        return ResponseData.OK(customerService.authByPwd(authUser));
    }

    @Limiter(type = LimiterType.IP, requestedTokens = 30, burstCapacity = 30)
    @GetMapping("/limiterTest")
    public ResponseData<?> limiterTest() {
        return ResponseData.OK();
    }

    public void authByMobile() {

    }

}
