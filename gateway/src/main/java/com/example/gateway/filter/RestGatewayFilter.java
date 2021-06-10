package com.example.gateway.filter;

import com.alibaba.fastjson.JSONObject;
import com.example.common.bean.ResponseData;
import com.example.common.entity.apiwhitelist.ApiWhitelist;
import com.example.common.utils.AntPathMatcherExt;
import com.example.common.utils.TokenUtils;
import com.example.service.ApiWhitelistService;
import org.springframework.beans.factory.annotation.Autowired;
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

import java.util.List;
import java.util.stream.Collectors;

/**
 * description: RestGatewayFilter <br>
 * date: 2020/10/21 14:27 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Component
public class RestGatewayFilter implements GatewayFilter, Ordered {
    @Autowired
    ApiWhitelistService apiWhitelistService;

    AntPathMatcherExt antPathMatcherExt = new AntPathMatcherExt();

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        String path = request.getPath().toString().replace("/admin", "");
        // 白名单
        List<ApiWhitelist> list = apiWhitelistService.listAll();
        List<String> collect = list.stream().map(ApiWhitelist::getPath).collect(Collectors.toList());
        if (antPathMatcherExt.pathMatch(collect, path)) {
            return chain.filter(exchange);
        }
        HttpHeaders headers = request.getHeaders();
        String token = headers.getFirst(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            return responseError(exchange);
        }
        TokenUtils.parser(token.replace("Bearer ", ""));
        return chain.filter( exchange );
    }

    @Override
    public int getOrder() {
        return 0;
    }

    public Mono<Void> responseError(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        ServerHttpResponse response = exchange.getResponse();
        ResponseData<Object> data = ResponseData.FAIL().setCode(HttpStatus.UNAUTHORIZED.value()).setMessage("Unauthorized");
        byte[] dataBytes = JSONObject.toJSONBytes(data);
        DataBuffer buffer = response.bufferFactory().wrap(dataBytes);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }
}
