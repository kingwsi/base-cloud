package com.example.rest;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;

import java.net.InetAddress;

@Slf4j
@SpringBootApplication(scanBasePackages = {"com.example"})
@MapperScan(basePackages = "com.example.mapper")
public class RestApplication implements ApplicationListener<WebServerInitializedEvent> {

    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    @SneakyThrows
    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        WebServer server = event.getWebServer();
        WebServerApplicationContext context = event.getApplicationContext();
        Environment env = context.getEnvironment();
        String activeProfiles = env.getProperty("spring.profiles.active");
        String ip = InetAddress.getLocalHost().getHostAddress();
        int port = server.getPort();
        log.info("\n---------------------------------------------------------" +
                "\n\t启动成功！" +
                "\n\t本地地址:\thttp://localhost:{}/swagger-ui.html" +
                "\n\t外部地址:\thttp://{}:{}/swagger-ui.html" +
                "\n\t配置:\t{}" +
                "\n---------------------------------------------------------\n", port, ip, port, activeProfiles);
    }

}
