package com.example.gateway.utils;

import com.example.common.bean.AuthUser;
import com.example.gateway.config.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * description: AdminAuthFegin <br>
 * date: 2020/9/28 13:48 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@FeignClient("admin")
public interface AdminAuthFeignClient {

    @RequestMapping(value = "/api/resource/apis", method = RequestMethod.GET)
    ResponseData<List<String>> listCurrentUserApis(@RequestParam String type, @RequestParam String userId);

    @RequestMapping(value = "/api/auth", method = RequestMethod.POST)
    ResponseData<String> createToken(@RequestBody AuthUser authUser);
}
