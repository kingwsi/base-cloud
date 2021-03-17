package com.example.common.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;

import java.util.List;

/**
 * description: RedisScript <br>
 * date: 2021/3/12 16:40 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Configuration
public class RedisConfiguration {
    @Bean
    @SuppressWarnings("unchecked")
    public org.springframework.data.redis.core.script.RedisScript limiterScript() {
        DefaultRedisScript redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(
                new ClassPathResource("request_rate_limiter.lua")));
        redisScript.setResultType(List.class);
        return redisScript;
    }
}
