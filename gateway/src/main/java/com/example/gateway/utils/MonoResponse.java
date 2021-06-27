package com.example.gateway.utils;

import com.alibaba.fastjson.JSONObject;
import com.example.common.bean.ResponseData;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

public class MonoResponse {
    public static Mono<Void> responseError(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        ServerHttpResponse response = exchange.getResponse();
        ResponseData<Object> data = ResponseData.FAIL().setCode(HttpStatus.UNAUTHORIZED.value()).setMessage("Unauthorized");
        byte[] dataBytes = JSONObject.toJSONBytes(data);
        DataBuffer buffer = response.bufferFactory().wrap(dataBytes);
        response.setStatusCode(HttpStatus.UNAUTHORIZED);
        response.getHeaders().add("Content-Type", "application/json;charset=UTF-8");
        return response.writeWith(Mono.just(buffer));
    }

    public static void parserAndBuildHeader(){

    }
}
