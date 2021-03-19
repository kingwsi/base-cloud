package com.example.gateway.config;

import com.example.gateway.filter.AdminGatewayFilter;
import com.example.gateway.filter.RestGatewayFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: GatewayRoutesConfiguration <br>
 * date: 2020/10/21 14:17 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Configuration
@Slf4j
public class GatewayRoutesConfiguration {

    /**
     * java 配置 server 服务路由
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/admin/**")
                        .filters(f -> f.stripPrefix(1)
                                .filters(new AdminGatewayFilter())
                        ).uri("lb://admin"))
                .route(r -> r.path("/rest/**")
                        .filters(f -> f.stripPrefix(1)
                                .filters(new RestGatewayFilter())
                        ).uri("lb://rest"))
                .build();
    }
}