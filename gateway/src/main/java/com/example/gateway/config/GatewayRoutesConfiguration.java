package com.example.gateway.config;

import com.example.gateway.feign.AdminAuthFeignClient;
import com.example.gateway.filter.AdminGatewayFilter;
import com.example.gateway.filter.RestGatewayFilter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

import java.util.List;

/**
 * description: GatewayRoutesConfiguration <br>
 * date: 2020/10/21 14:17 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Configuration
@Slf4j
public class GatewayRoutesConfiguration {

    @Autowired
    private RedisTemplate<String, List<String>> redisTemplate;

    @Lazy
    @Autowired
    private AdminAuthFeignClient adminAuthFeignClient;

    /**
     * java 配置 server 服务路由
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routeLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route(r -> r.path("/base-admin/**")
                        .and().readBody(String.class, requestBody -> true)
                        .filters(f -> f.stripPrefix(1)
                                .filters(new AdminGatewayFilter(redisTemplate, adminAuthFeignClient))
                        ).uri("lb://base-admin"))
                .route(r -> r.path("/base-rest/**")
                        // 读取Request Body
                        .and().readBody(String.class, requestBody -> true)
                        .filters(f -> f.stripPrefix(1)
                                .filters(new RestGatewayFilter(redisTemplate, adminAuthFeignClient))
                        ).uri("lb://base-rest"))
                .build();
    }

    /**
     * 配置跨域
     * @return
     */
    @Bean
    public CorsWebFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        // cookie跨域
        config.setAllowCredentials(Boolean.TRUE);
        config.addAllowedMethod("*");
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        // 配置前端js允许访问的自定义响应头
        config.addExposedHeader("Authorization:");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}
