package com.example.gateway.utils;

import com.example.common.utils.AntPathMatcherExt;
import com.example.gateway.config.ResponseData;
import com.example.gateway.feign.AdminAuthFeignClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

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

    private static AntPathMatcherExt antPathMatcher = new AntPathMatcherExt();

    public PermissionUtils(AdminAuthFeignClient adminAuthFeignClient) {
        PermissionUtils.adminAuthFeignClient = adminAuthFeignClient;
    }

    public static boolean checkPathPermission(String path, String method, String userId) {
        ResponseData<List<String>> apis = adminAuthFeignClient.listCurrentUserApis(method, userId);
        if (apis.getCode() == 200 && !apis.getData().isEmpty()) {
            return antPathMatcher.pathMatch(apis.getData().toArray(new String[]{}), path);
        }
        log.warn("unauthorized -> user:{} path:{} {}", userId, method, path);
        return false;
    }
}
