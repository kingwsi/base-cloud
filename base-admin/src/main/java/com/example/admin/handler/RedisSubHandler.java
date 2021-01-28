package com.example.admin.handler;

import com.example.common.enumerate.RedisKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * description: RedisSubHandler <br>
 * date: 2021/1/28 15:28 <br>
 * author: ws <br>
 * version: 1.0 <br>
 */
@Slf4j
@Component
public class RedisSubHandler {
    public void receiverMessage(String message) {
        log.info(RedisKey.GATEWAY_LOG_FILTER.getDescription() + "recover->{}", message);
        // TODO 日志持久化
    }
}
