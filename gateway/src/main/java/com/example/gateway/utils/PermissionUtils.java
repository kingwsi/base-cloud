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

    private static RedisTemplate<Object, Object> redisTemplate;

    private static AntPathMatcherExt antPathMatcher = new AntPathMatcherExt();

    public PermissionUtils(AdminAuthFeignClient adminAuthFeignClient, RedisTemplate<Object, Object> redisTemplate) {
        PermissionUtils.adminAuthFeignClient = adminAuthFeignClient;
        PermissionUtils.redisTemplate = redisTemplate;
    }

    /**
     * 权限校验
     *
     * @param path   请求路径
     * @param method 请求方式
     * @param userId 用户ID
     * @return 允许访问返回true
     */
    public static boolean isAllowed(String path, String method, Integer userId) {
        String[] apisArray = (String[]) redisTemplate.opsForValue().get("permission:" + method + ":" + userId);
        if (apisArray == null) {
            ResponseData<List<String>> responseData = adminAuthFeignClient.listCurrentUserApis(method, userId);
            if (responseData.getCode() == 200 && !responseData.getData().isEmpty()) {
                apisArray = responseData.getData().toArray(new String[]{});
                redisTemplate.opsForValue().set("permission:" + method + ":" + userId, apisArray, 10, TimeUnit.MINUTES);
            } else {
                return false;
            }
        }
        if (antPathMatcher.pathMatch(apisArray, path)) {
            return true;
        }
        log.warn("unauthorized -> user:{} path:{} {}", userId, method, path);
        return false;
    }
}
