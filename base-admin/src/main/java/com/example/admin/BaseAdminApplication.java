package com.example.admin;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(scanBasePackages = {"com.example"})
@MapperScan(basePackages = "com.example.mapper")
@EnableFeignClients
@Slf4j
public class BaseAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(BaseAdminApplication.class, args);
    }
}
