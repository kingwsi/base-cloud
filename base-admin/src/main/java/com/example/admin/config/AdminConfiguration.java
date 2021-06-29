package com.example.admin.config;

import com.baomidou.mybatisplus.core.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.extension.incrementer.H2KeyGenerator;
import com.example.admin.handler.RedisSubHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * description: AdminConfiguration <br>
 * date: 2020/9/30 13:09 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Configuration
public class AdminConfiguration {
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     *
     * @param receiver
     * @return
     */
    @Bean
    MessageListenerAdapter listenerAdapter(RedisSubHandler receiver) {
        //给messageListenerAdapter 传入一个消息接受的处理器，利用反射的方法调用“receiveMessage”
        //不填defaultListenerMethod默认调用handleMessage
        return new MessageListenerAdapter(receiver, "receiverMessage");
    }

    @Bean
    public IKeyGenerator keyGenerator() {
        return new H2KeyGenerator();
    }
}
