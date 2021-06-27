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

    private final RedisTemplate<String, Object> redisTemplate;

    private final AdminAuthFeignClient adminAuthFeignClient;

    public RestGatewayFilter(RedisTemplate<String, Object> redisTemplate, AdminAuthFeignClient adminAuthFeignClient) {
        this.redisTemplate = redisTemplate;
        this.adminAuthFeignClient = adminAuthFeignClient;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString().replace("/base-rest", "");
        // 白名单
        redisTemplate.delete("rest_whitelist:" + Objects.requireNonNull(request.getMethod()).toString());
        Object whitelistObject = redisTemplate.opsForValue().get("rest_whitelist:" + Objects.requireNonNull(request.getMethod()).toString());
        List<String> whitelist = Collections.emptyList();
        if (whitelistObject == null) {
            ResponseData<List<String>> listResponseData = adminAuthFeignClient.listApiWhitelist();
            whitelist = Optional.ofNullable(listResponseData).map(ResponseData::getData).map(list -> {
                redisTemplate.opsForValue().set("rest_whitelist:" + Objects.requireNonNull(request.getMethod()).toString(), list, 1, TimeUnit.DAYS);
                return list;
            }).orElse(Collections.emptyList());
        } else {
            whitelist = (List<String>) whitelistObject;
        }
        // 白名单
        if (antPathMatcherExt.pathMatch(whitelist, path)) {
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            return MonoResponse.responseError(exchange);
        }
        AuthUser authUser = TokenUtils.parserMember(token.replace("Bearer ", ""));
        //将数据返回给下级服务器
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set(RequestHeader.PRINCIPAL_ID.name(), String.valueOf(authUser.getId()));
            httpHeader.set(RequestHeader.PRINCIPAL_NAME.name(), authUser.getUsername());
        };
        //将现在的request，添加当前身份
        ServerHttpRequest mutableReq = exchange.getRequest().mutate().headers(httpHeaders).build();
        ServerWebExchange mutableExchange = exchange.mutate().request(mutableReq).build();
        //将数据返回给下级服务器
        return chain.filter(mutableExchange);
    }

    @Override
    public int getOrder() {
        return 0;
    }
}
