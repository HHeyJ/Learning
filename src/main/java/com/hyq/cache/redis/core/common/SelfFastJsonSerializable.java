package com.hyq.cache.redis.core.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.StandardCharsets;

/**
 * @author nanke
 * @date 2020/3/19 下午6:32
 * @deprecated 自定义fastjson序列化方式
 */
public class SelfFastJsonSerializable<T> implements RedisSerializer<T> {

    /**
     * 序列化目标类
     */
    private Class<T> clazz;

    public SelfFastJsonSerializable(Class<T> clazz) {
        super();
        this.clazz = clazz;
    }

    @Override
    public byte[] serialize(Object o) throws SerializationException {

        return o == null ? new byte[0] : JSON.toJSONString(o, SerializerFeature.WriteClassName).getBytes(StandardCharsets.UTF_8);
    }

    @Override
    public T deserialize(byte[] bytes) throws SerializationException {

        return bytes == null || bytes.length <= 0 ? null : JSONObject.parseObject(new String(bytes, StandardCharsets.UTF_8),clazz);
    }
}
