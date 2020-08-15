package com.hyq.redis.controller;

import com.hyq.redis.core.result.Result;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nanke
 * @date 2020/3/22 下午10:36
 */
@RestController
public class SpecialityTestController {

    @Resource
    private RedisTemplate template;

    @RequestMapping("/sendMessage")
    public void sendMessage() {
        template.convertAndSend("B","狗日的快接");
    }

    @RequestMapping("tansExecute")
    public Result<Object> tansExecute() {

        String key = "xx";
        Object o = template.execute(new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                // 监控某key,该key发生变化时候不操作该key
                operations.watch(key);
                // 事物开启
                operations.multi();
                // 业务操作
                try {
                    // 用于手动模拟其他客户端改动该key
                    Thread.sleep(3000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                operations.opsForValue().increment(key);
                operations.opsForValue().increment(key);
                // 事物提交
                return operations.exec();
            }
        });
        return Result.buildSuccess(o);
    }
}
