package com.example.gateway.filter;

import com.example.common.bean.AuthUser;
import com.example.common.bean.ResponseData;
import com.example.common.enumerate.RequestHeader;
import com.example.common.utils.AntPathMatcherExt;
import com.example.common.utils.TokenUtils;
import com.example.gateway.feign.AdminAuthFeignClient;
import com.example.gateway.utils.MonoResponse;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

/**
 * description: RestGatewayFilter <br>
 * date: 2020/10/21 14:27 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Component
public class RestGatewayFilter implements GatewayFilter, Ordered {

    AntPathMatcherExt antPathMatcherExt = new AntPathMatcherExt();

    private final RedisTemplate<String, List<String>> redisTemplate;

    private final AdminAuthFeignClient adminAuthFeignClient;

    public RestGatewayFilter(RedisTemplate<String, List<String>> redisTemplate, AdminAuthFeignClient adminAuthFeignClient) {
        this.redisTemplate = redisTemplate;
        this.adminAuthFeignClient = adminAuthFeignClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString().replace("/base-rest", "");
        // 白名单
        String WHITELIST_KEY = "rest_api_whitelist:" + Objects.requireNonNull(request.getMethod()).toString();
        List<String> whitelist = redisTemplate.opsForValue().get(WHITELIST_KEY);
        if (whitelist == null) {
            ResponseData<List<String>> listResponseData = adminAuthFeignClient.listApiWhitelist();
            if (listResponseData.getData() != null) {
                whitelist = listResponseData.getData();
                redisTemplate.opsForValue().set(WHITELIST_KEY, whitelist, 1, TimeUnit.DAYS);
            }
        }
        if (antPathMatcherExt.pathMatch(whitelist, path)) {
            return chain.filter(exchange);
        }
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            return MonoResponse.responseError(exchange);
        }
        try {
            AuthUser authUser = TokenUtils.parserMember(token.replace("Bearer ", ""));
            //httpHeaders处理
            Consumer<HttpHeaders> httpHeaders = httpHeader -> {
                httpHeader.set(RequestHeader.PRINCIPAL_ID.name(), String.valueOf(authUser.getId()));
                httpHeader.set(RequestHeader.PRINCIPAL_NAME.name(), authUser.getUsername());
            };
            ServerHttpRequest mutableReq = exchange.getRequest().mutate().headers(httpHeaders).build();
            ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
            return chain.filter(mutableExchange);
        } catch (Exception e) {
            return MonoResponse.responseError(exchange);
        }
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
