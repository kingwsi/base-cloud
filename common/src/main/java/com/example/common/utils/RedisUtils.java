package com.example.common.utils;

import com.example.common.enumerate.RedisConstKey;
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

    public static void set(RedisConstKey redisConstKey, String val) {
        redisTemplate.opsForValue().set(redisConstKey.getKey(), val, redisConstKey.getExpire(), redisConstKey.getTimeUnit());
    }

    public static Object get(RedisConstKey redisConstKey) {
        return redisTemplate.opsForValue().get(redisConstKey.getKey());
    }
}
