package com.example.gateway.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: GlobalConfiguration <br>
 * date: 2020/9/27 12:59 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Configuration
public class GlobalConfiguration {

//    @Bean
    RedisRateLimiter redisRateLimiter() {
        return new RedisRateLimiter(1, 2);
    }

    @Bean
    HttpMessageConverters httpMessageConverters() {
        return new HttpMessageConverters();
    }
}
