package com.example.gateway.filter;

import com.example.common.enumerate.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * description: 请求日志过滤器 <br>
 * date: 2021/1/28 13:40 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Component
@Slf4j
public class LoggerFilter implements GlobalFilter, Ordered {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        long startTime = System.currentTimeMillis();
        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long executeTime = (System.currentTimeMillis() - startTime);
            String format = String.format("{\"remoteHost\":\"%s\",\"method\":\"%s\",\"path\":\"%s\",\"executeTime\":\"%s\"}", request.getMethod(), request.getURI().getHost(), request.getURI().getPath(), executeTime);
            log.info(format);
            stringRedisTemplate.convertAndSend(RedisKey.GATEWAY_LOG_FILTER.name(), format);
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }
}
