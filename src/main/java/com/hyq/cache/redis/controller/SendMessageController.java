package com.hyq.cache.redis.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nanke
 * @date 2020/3/22 下午10:36
 */
@RestController
public class SendMessageController {

    @Resource
    private RedisTemplate template;

    @RequestMapping("/sendMessage")
    public void sendMessage() {
        template.convertAndSend("B","狗日的快接");
    }
}
