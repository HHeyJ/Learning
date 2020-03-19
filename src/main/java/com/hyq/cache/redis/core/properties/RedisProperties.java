package com.hyq.cache.redis.core.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author nanke
 * @date 2020/3/19 下午3:10
 */
@Data
@Component
@ConfigurationProperties(prefix = "redis")
public class RedisProperties {

    /**
     * ip
     */
    private String host;
    /**
     * 端口
     */
    private Integer port;
    /**
     * 密码
     */
    private String password;
    /**
     * 库
     */
    private Integer index;
    /**
     * 最大空闲
     */
    private Integer maxIdle;
    /**
     * 最大连接
     */
    private Integer maxTotal;
}
