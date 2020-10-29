package com.example.gateway.utils;

import com.example.gateway.config.ResponseData;
import com.example.gateway.feign.AdminAuthFeignClient;
import com.example.gateway.filter.AdminGatewayFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * description: TestController <br>
 * date: 2020/10/29 15:44 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@RestController
public class TestController {
    @Autowired
    AdminAuthFeignClient adminAuthFeignClient;

    @GetMapping("/test")
    public String test(){
        ResponseData<List<String>> get = adminAuthFeignClient.listCurrentUserApis("GET", "1");
        return "SUCCESS";
    }
}
