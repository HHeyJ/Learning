package com.hyq.cache.redis.core.common;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author nanke
 * @date 2020/3/19 下午5:36
 */
public class RedisExecoter {

    private RedisTemplate<Serializable,Object> template;

    public RedisExecoter(RedisTemplate<Serializable, Object> template) {
        this.template = template;
    }

    /**
     * kv类型设置
     * @param key
     * @param value
     * @param expire
     */
    public <T extends Object> void kvSet(String key, T value, Long expire) {
        template.opsForValue().set(key,value);
        if (expire != null)
            template.expire(key, expire,TimeUnit.SECONDS);
    }

    /**
     * kv类型获取
     * @param key
     * @return
     */
    public <T extends Object> T kvGet(String key) {
        return (T) template.opsForValue().get(key);
    }

    /**
     * set无序集合设置
     * @param key
     * @param value
     * @param expire
     * @param <T>
     */
    public <T extends Object> Long sSet(String key, T value, Long expire) {
        // TODO Lua脚本设置
        Boolean hasKey = template.hasKey(key);
        hasKey = hasKey == null ? false : hasKey;
        Long result = template.opsForSet().add(key, value);
        if (expire != null && !hasKey)
            template.expire(key, expire,TimeUnit.SECONDS);
        return result;
    }

    /**
     * set无序集合获取
     * @param key
     * @param <T>
     */
    public <T extends Object> Set<T> sGet(String key) {
        return (Set<T>) template.opsForSet().members(key);
    }

    /**
     * zSet有序集合设置
     * @param key
     * @param value
     * @param expire
     * @param <T>
     */
    public <T extends Object> void zSet(String key, T value, Long score, Long expire) {
        template.opsForZSet().add(key,value,score);
        if (expire != null)
            template.expire(key, expire,TimeUnit.SECONDS);
    }

    /**
     * zSet按照成绩倒序分页
     * @param key
     * @param pageNo
     * @param pageSize
     * @param <T>
     */
    public <T extends Object> Set<T> zGet(String key, Integer pageNo, Integer pageSize) {
        return (Set<T>) template.opsForZSet().reverseRange(key, (pageNo - 1) * pageSize, pageNo * pageSize - 1);
    }

    /**
     * zSet成绩范围内个数
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zCount(String key, double min, double max) {
        return template.opsForZSet().count(key,min,max);
    }

    /**
     * zSet获取对象成绩
     * @param key
     * @param obj
     * @param <T>
     * @return
     */
    public <T extends Object> Long zGetScore(String key, T obj) {
        return template.opsForZSet().score(key,obj).longValue();
    }

    /**
     * zSet增加对象成绩
     * @param key
     * @param obj
     * @param score
     * @param <T>
     * @return
     */
    public <T extends Object> Long zIncrby(String key, T obj, Long score) {
        return template.opsForZSet().incrementScore(key,obj,score).longValue();
    }

    /**
     * hash循环设置属性
     * @param key
     * @param propertiesMap
     */
    public <T extends Object> void hashPut(String key, Map<Object,T> propertiesMap) {
        propertiesMap.entrySet().forEach(hashEntry ->
                template.opsForHash().putIfAbsent(key,hashEntry.getKey(),hashEntry.getValue()));
    }

    /**
     * hash获取散列所有属性
     * @param key
     * @param hashKeys
     * @param <T>
     * @return
     */
    public <T extends Object> Map<Object,T> hashGet(String key, List<Object> hashKeys) {
        Map<Object,T> propertiesMap = new HashMap<>();
        List<T> hashValues = (List<T>) template.opsForHash().multiGet(key, hashKeys);

        for (int i = 0; i < hashKeys.size(); i++) {
            propertiesMap.put(hashKeys.get(i),hashValues.get(i));
        }
        return propertiesMap;
    }


}
