package com.example.gateway.filter;

import com.example.common.enumerate.RedisConstKey;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.ReactiveHttpInputMessage;
import org.springframework.http.codec.HttpMessageReader;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyExtractor;
import org.springframework.web.reactive.function.BodyExtractors;
import org.springframework.web.reactive.function.server.HandlerStrategies;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

/**
 * description: 请求日志过滤器 <br>
 * date: 2021/1/28 13:40 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
//@Component
@Slf4j
public class LoggerFilter implements GlobalFilter, Ordered {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private ServerCodecConfigurer codecConfigurer;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();
        long startTime = System.currentTimeMillis();
        String requestBody = getRequestBody(exchange);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            long executeTime = (System.currentTimeMillis() - startTime);
            String format = String.format("{\"remoteHost\":\"%s\",\"method\":\"%s\",\"path\":\"%s\",\"executeTime\":\"%s\"}", request.getURI().getHost(), request.getMethod(), request.getURI().getPath(), executeTime);
            log.info(format);
            log.info(requestBody);
//            stringRedisTemplate.convertAndSend(RedisConstKey.GATEWAY_LOG_FILTER.name(), format);
        }));
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE;
    }

    /**
     * <a href="https://github.com/spring-cloud/spring-cloud-gateway/issues/152">Get Request Body issues</a>
     */
    public String getRequestBody(ServerWebExchange exchange){
        try {
            ByteBuffer byteBuffer = Mono.from(exchange.getRequest().getBody()).toFuture().get().asByteBuffer();
            byte[] bytes = new byte[byteBuffer.capacity()];
            while (byteBuffer.hasRemaining()) {
                byteBuffer.get(bytes);
            }
            return new String(bytes, StandardCharsets.UTF_8);
        } catch (InterruptedException | ExecutionException e) {
            log.warn("Get Request Body Exception: {}", e.toString());
            return "NULL";
        }
    }
}
