package com.example.rest.api;

import com.example.service.CustomerService;
import com.example.common.bean.AuthUser;
import com.example.common.bean.ResponseData;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: CustomerController <br>
 * date: 2020/10/20 14:19 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/login")
    public ResponseData login(AuthUser authUser){
        return ResponseData.OK(customerService.getToken(authUser));
    }

    @PostMapping("/info")
    public ResponseData getInfo(AuthUser authUser){
        return ResponseData.OK(customerService.getCustomerInfo(""));
    }
}
