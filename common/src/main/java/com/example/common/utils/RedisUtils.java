package com.example.common.utils;

import com.example.common.enumerate.RedisKey;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

/**
 * @Author wangshu
 * @Description TODO
 * @CreateTime 2021/03/24 15:36:00
 */
@Component
public class RedisUtils {
    private static RedisTemplate<String, Object> redisTemplate = null;

    public RedisUtils(RedisTemplate<String, Object> template) {
        redisTemplate = template;
    }

    public static void set(RedisKey redisKey, String val) {
        redisTemplate.opsForValue().set(redisKey.getKey(), val, redisKey.getExpire(), redisKey.getTimeUnit());
    }

    public static Object get(RedisKey redisKey) {
        return redisTemplate.opsForValue().get(redisKey.getKey());
    }
}
