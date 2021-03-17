package com.example.common.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

/**
 * description: redis请求限制工具类 <br>
 * date: 2021/3/16 9:33 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Slf4j
public class LimiterUtils {
    public static List<String> getKeys(String key) {
        // use `{}` around keys to use Redis Key hash tags
        // this allows for using redis cluster

        // Make a unique key per user.
        String prefix = "request_rate_limiter.{" + key;

        // You need two Redis keys for Token Bucket.
        String tokenKey = prefix + "}.tokens";
        String timestampKey = prefix + "}.timestamp";
        return Arrays.asList(tokenKey, timestampKey);
    }
}
