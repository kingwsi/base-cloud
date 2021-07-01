package com.example.common.configuration;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HttpMessageConvertConfig {

    @Bean
    public HttpMessageConverters httpMessageConverters(){
        return new HttpMessageConverters();
    }


}
