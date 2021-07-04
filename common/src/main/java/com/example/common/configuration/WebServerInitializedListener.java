package com.example.common.configuration;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.net.InetAddress;

/**
 * description: ApplicationAconfig <br>
 * date: 2021/3/17 13:18 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Slf4j
@Configuration
public class WebServerInitializedListener implements ApplicationListener<WebServerInitializedEvent> {

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
                "\n\t启动成功: {}" +
                "\n\t本地地址: http://localhost:{}/swagger-ui/index.html" +
                "\n\t外部地址: http://{}:{}" +
                "\n---------------------------------------------------------\n", activeProfiles, port, ip, port);
    }
}
