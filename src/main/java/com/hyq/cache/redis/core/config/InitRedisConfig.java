package com.hyq.cache.redis.core.config;

import com.alibaba.fastjson.parser.ParserConfig;
import com.hyq.cache.redis.core.common.RedisExecoter;
import com.hyq.cache.redis.core.common.SelfFastJsonSerializable;
import com.hyq.cache.redis.core.properties.RedisProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author nanke
 * @date 2020/3/19 下午3:06
 */
@Configuration
public class InitRedisConfig {

    @Resource
    private RedisProperties redisProperties;

    /**
     * 连接池配置类～
     * @return
     */
    @Bean(name = "poolConfig")
    public JedisPoolConfig jedisPoolConfig() {

        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisProperties.getMaxIdle());
        config.setMaxTotal(redisProperties.getMaxTotal());
        return config;
    }

    /**
     * 连接工厂～
     * @param jedisPoolConfig
     * @return
     */
    @Bean(name = "connFactory")
    public JedisConnectionFactory jedisConnFactory(@Qualifier("poolConfig") JedisPoolConfig jedisPoolConfig) {

        RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
        redisConfiguration.setHostName(redisProperties.getHost());
        redisConfiguration.setPassword(redisProperties.getPassword());
        redisConfiguration.setPort(redisProperties.getPort());
        redisConfiguration.setDatabase(redisProperties.getIndex());

        JedisClientConfiguration clientConfiguration =
                JedisClientConfiguration.builder()
                        .usePooling()
                        .poolConfig(jedisPoolConfig)
                        .build();
        return new JedisConnectionFactory(redisConfiguration,clientConfiguration);
    }

    /**
     * 序列化模版～
     * @param connFactory
     * @return
     */
    @Bean(name = "template")
    public RedisTemplate<Serializable,Object> template(@Qualifier("connFactory") JedisConnectionFactory connFactory) {

        RedisTemplate<Serializable,Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connFactory);

        ParserConfig.getGlobalInstance().setAutoTypeSupport(true);
        SelfFastJsonSerializable<Object> valueSeriable = new SelfFastJsonSerializable(Object.class);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(valueSeriable);
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(valueSeriable);
        return template;
    }

    /**
     * 包装业务类～
     * @return
     */
    @Bean(name = "redisExecoter")
    public RedisExecoter redisExecoter(@Qualifier("template") RedisTemplate<Serializable,Object> template) {

        return new RedisExecoter(template);
    }

}
