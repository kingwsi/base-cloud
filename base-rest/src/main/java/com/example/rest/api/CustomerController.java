package com.example.rest.api;

import com.example.common.bean.AuthUser;
import com.example.common.bean.ResponseData;
import com.example.service.CustomerService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseData register(@RequestBody AuthUser authUser) {
        customerService.register(authUser);
        return ResponseData.OK();
    }

    @PostMapping("/authByPwd")
    public ResponseData authByPwd(@RequestBody AuthUser authUser) {
        return ResponseData.OK(customerService.authByPwd(authUser));
    }

    public void authByMobile() {

    }
    
}
