package com.example.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.common.bean.AuthUser;
import com.example.common.bean.ResponseData;
import com.example.common.enumerate.RequestHeader;
import com.example.common.utils.AntPathMatcherExt;
import com.example.common.utils.TokenUtils;
import com.example.gateway.utils.MonoResponse;
import com.example.gateway.utils.PermissionUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@Component
public class AdminGatewayFilter implements GatewayFilter, Ordered {

    AntPathMatcherExt antPathMatcherExt = new AntPathMatcherExt();

    private static final String[] excludedAuthPages = {
            "/api/debug/**",
            "/api/auth",
            "/api/verification/captcha",
            "/api/customer/login",
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString().replace("/base-admin", "");
        // 白名单
        if (antPathMatcherExt.pathMatch(excludedAuthPages, path)) {
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            return MonoResponse.responseError(exchange);
        }
        AuthUser authUser;
        try {
            authUser = TokenUtils.parser(token.replace("Bearer ", ""));
        } catch (Exception e) {
            log.error("Token解析失败->{}", e.getMessage());
            return MonoResponse.responseError(exchange);
        }

        if (!PermissionUtils.isAllowed(path, Objects.requireNonNull(request.getMethod()).name(), authUser.getId())) {
            return MonoResponse.responseError(exchange);
        }

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
        return -1;
    }
}
