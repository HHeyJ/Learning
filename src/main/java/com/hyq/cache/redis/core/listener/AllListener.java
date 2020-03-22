package com.hyq.cache.redis.core.listener;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author nanke
 * @date 2020/3/22 下午10:25
 */
@Component
public class AllListener implements MessageListener {

    @Resource
    private RedisTemplate template;

    @Override
    public void onMessage(Message message, byte[] pattern) {

        RedisSerializer valueSerializer = template.getValueSerializer();
        Object deserialize = valueSerializer.deserialize(message.getBody());
        System.out.println(deserialize);
    }
}

