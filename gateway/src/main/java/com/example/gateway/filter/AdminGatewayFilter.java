package com.example.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.common.bean.AuthUser;
import com.example.common.utils.AntPathMatcherExt;
import com.example.common.utils.TokenUtils;
import com.example.gateway.config.ResponseData;
import com.example.gateway.utils.AdminAuthFeignClient;
import com.example.gateway.utils.BaseUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
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

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.function.Consumer;

@Slf4j
@Component
public class AdminGatewayFilter implements GatewayFilter, Ordered {

    private AntPathMatcherExt antPathMatcher = new AntPathMatcherExt();

    @Autowired
    private AdminAuthFeignClient adminAuthFeignClient;

    private final String KEY = "123456";

    private static final String[] excludedAuthPages = {
            "/api/debug/**",
            "/admin/api/auth",
            "/rest/api/customer/login",
            "/webjars/**",
            "/swagger**/**",
            "/v2/api-docs**",
            "/h2-console/**",
            "/**/*.gif", "/**/*.png", "/**/*.jpg", "/**/*.html", "/**/*.js", "/**/*.css", "/**/*.ico",
            "/health",
            "/api/socket/**"
    };

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString();
        if (BaseUtils.pathMatch(excludedAuthPages, path)) {
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            return responseError(exchange);
        }
        AuthUser authUser = TokenUtils.parser(token.replace("Bearer ", ""));

        if (!checkPathPermission(path, Objects.requireNonNull(request.getMethod()).name(), authUser.getId())) {
            return responseError(exchange);
        }

        //将数据返回给下级服务器
        Consumer<HttpHeaders> httpHeaders = httpHeader -> {
            httpHeader.set("x-id", authUser.getId());
            httpHeader.set("x-name", authUser.getUsername());
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

    public Mono<Void> responseError(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        ServerHttpResponse response = exchange.getResponse();
        ResponseData<String> data = new ResponseData<>();
        data.setCode(HttpStatus.UNAUTHORIZED.value());
        data.setMessage("Unauthorized");
        byte[] dataBytes = JSONObject.toJSONBytes(data);
        DataBuffer buffer = response.bufferFactory().wrap(dataBytes);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    public boolean checkPathPermission(String path, String method, String userId) {
        ResponseData<List<String>> apis = adminAuthFeignClient.listCurrentUserApis(method, userId);
        if (apis.getCode() == 200 && !apis.getData().isEmpty()) {
            return antPathMatcher.pathMatch((String[]) apis.getData().toArray(), path);
        }
        log.info("unauthorized -> user:{} path:{} {}", userId, method, path);
        return false;
    }
}
