package com.example.rest.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * description: CustomConfiguration <br>
 * date: 2020/10/20 14:44 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Configuration
public class CustomConfiguration {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
