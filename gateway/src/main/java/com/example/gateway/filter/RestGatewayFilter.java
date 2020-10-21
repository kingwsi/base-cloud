package com.example.gateway.filter;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * description: RestGatewayFilter <br>
 * date: 2020/10/21 14:27 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
public class RestGatewayFilter implements GatewayFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // TODO REST请求过滤
        return chain.filter( exchange );
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
