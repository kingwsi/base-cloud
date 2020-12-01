package com.example.admin.config;

import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.spring.boot.autoconfigure.properties.DruidStatProperties;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * description: DruidConfig <br>
 * date: 2020/12/1 11:16 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Configuration
public class DruidConfig {
    @Bean
    public StatFilter stat() {
        return new StatFilter();
    }
}
