package com.example.gateway.utils;

import com.example.common.bean.ResponseData;
import com.example.common.utils.AntPathMatcherExt;
import com.example.gateway.feign.AdminAuthFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * description: PermissionUtils <br>
 * date: 2020/10/29 16:24 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Slf4j
@Component
public class PermissionUtils {

    private static AdminAuthFeignClient adminAuthFeignClient;

    private static RedisTemplate<String, Object> redisTemplate;

    private static final AntPathMatcherExt antPathMatcher = new AntPathMatcherExt();

    public PermissionUtils(AdminAuthFeignClient adminAuthFeignClient, RedisTemplate<String, Object> redisTemplate) {
        PermissionUtils.adminAuthFeignClient = adminAuthFeignClient;
        PermissionUtils.redisTemplate = redisTemplate;
    }



    public void Test(){

    }
}
