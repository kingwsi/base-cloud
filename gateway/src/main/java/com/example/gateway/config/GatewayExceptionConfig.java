package com.example.gateway.config;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.api.R;
import com.example.common.bean.ResponseData;
import com.example.gateway.utils.MonoResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.reactive.error.ErrorWebExceptionHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

/**
 * description: TODO <br>
 * date: 2021/06/29 14:16 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Slf4j
@Order(-1)
@Configuration
@RequiredArgsConstructor
public class GatewayExceptionConfig implements ErrorWebExceptionHandler {

    private final ObjectMapper objectMapper;

    @Override
    public Mono<Void> handle(ServerWebExchange exchange, Throwable ex) {
        ServerHttpResponse response = exchange.getResponse();

        if (response.isCommitted()) {
            return Mono.error(ex);
        }

        // header set
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        if (ex instanceof ResponseStatusException) {
            response.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        ResponseData<Object> data = ResponseData.FAIL().setCode(Objects.requireNonNull(response.getStatusCode()).value()).setMessage(ex.getMessage());
        return response
                .writeWith(Mono.fromSupplier(() -> {
                    DataBufferFactory bufferFactory = response.bufferFactory();
                    return bufferFactory.wrap(JSONObject.toJSONBytes(data));
                }));
//        return response
//                .writeWith(Mono.fromSupplier(() -> {
//                    DataBufferFactory bufferFactory = response.bufferFactory();
//                    try {
//                        return bufferFactory.wrap(objectMapper.writeValueAsBytes(R.failed(ex.getMessage())));
//                    } catch (JsonProcessingException e) {
//                        log.warn("Error writing response", ex);
//                        return bufferFactory.wrap(new byte[0]);
//                    }
//                }));
    }
}
