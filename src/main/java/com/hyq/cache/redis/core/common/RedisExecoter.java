package com.hyq.cache.redis.core.common;

import org.springframework.data.redis.core.RedisTemplate;

import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * @author nanke
 * @date 2020/3/19 下午5:36
 */
public class RedisExecoter<K extends Serializable,V> {

    private RedisTemplate<Serializable,Object> template;

    public RedisExecoter(RedisTemplate<Serializable, Object> template) {
        this.template = template;
    }

    /**
     * key是否存在
     * @param key
     * @return
     */
    public Boolean hasKey(K key) {
        return template.hasKey(key);
    }

    /**
     * 散列Key是否存在
     * @param key
     * @param hashKey
     * @param <HK>
     * @return
     */
    public <HK> Boolean hashKey(K key, HK hashKey) {
        return template.opsForHash().hasKey(key,hashKey);
    }

    /**
     * 散列Key删除
     * @param key
     * @param hashKey
     * @param <HK>
     * @return
     */
    public <HK> Long deleteKey(K key, HK hashKey) {
        return template.opsForHash().delete(key,hashKey);
    }

    /**
     * kv类型设置
     * @param key
     * @param value
     * @param expire
     */
    public void kvSet(K key, V value, Long expire) {
        template.opsForValue().set(key,value);
        if (expire != null)
            template.expire(key, expire,TimeUnit.SECONDS);
    }

    /**
     * kv类型获取
     * @param key
     * @return
     */
    public V kvGet(K key) {
        return (V) template.opsForValue().get(key);
    }

    /**
     * set无序集合设置
     * @param key
     * @param value
     * @param expire
     */
    public Long sSet(K key, V value, Long expire) {
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
     */
    public Set<V> sGet(K key) {
        return (Set<V>) template.opsForSet().members(key);
    }

    /**
     * zSet有序集合设置
     * @param key
     * @param value
     * @param expire
     */
    public void zSet(K key, V value, Long score, Long expire) {
        template.opsForZSet().add(key,value,score);
        if (expire != null)
            template.expire(key, expire,TimeUnit.SECONDS);
    }

    /**
     * zSet成绩范围内个数
     * @param key
     * @param min
     * @param max
     * @return
     */
    public Long zCount(K key, double min, double max) {
        return template.opsForZSet().count(key,min,max);
    }

    /**
     * zSet成员个数
     * @param key
     * @return
     */
    public Long zCard(K key) {
        return template.opsForZSet().zCard(key);
    }

    /**
     * zSet按照成绩倒序分页
     * @param key
     * @param pageNo
     * @param pageSize
     */
    public Set<V> zGet(K key, Integer pageNo, Integer pageSize) {
        return (Set<V>) template.opsForZSet().reverseRange(key, (pageNo - 1) * pageSize, pageNo * pageSize - 1);
    }

    /**
     * zSet获取对象成绩
     * @param key
     * @param obj
     * @return
     */
    public Long zGetScore(K key, V obj) {
        return template.opsForZSet().score(key,obj).longValue();
    }

    /**
     * zSet增加对象成绩
     * @param key
     * @param obj
     * @param score
     * @return
     */
    public Long zIncrby(K key, V obj, Long score) {
        return template.opsForZSet().incrementScore(key,obj,score).longValue();
    }

    /**
     * hash设置属性
     * @param key
     * @param propertiesMap
     */
    public <K extends Serializable, HK, HV> void hashPut(K key, Map<HK,HV> propertiesMap, Long expire) {
        template.opsForHash().putAll(key,propertiesMap);
        if (expire != null)
            template.expire(key, expire,TimeUnit.SECONDS);
    }

    /**
     * hash设置属性
     * @param key
     * @param hashKey
     * @param hashValue
     * @param <K>
     * @param <HK>
     * @param <HV>
     */
    public <K extends Serializable, HK, HV> void hashPut(K key, HK hashKey, HV hashValue) {
        template.opsForHash().put(key,hashKey,hashValue);
    }

    /**
     * hash获取散列单个属性
     * @param key
     * @param hashKey
     * @param <HK>
     * @return
     */
    public <HK, HV> HV hashGet(K key, HK hashKey) {
        HV hashValue = (HV) template.opsForHash().get(key, hashKey);
        return hashValue;
    }

    /**
     * hash获取散列所有属性
     * @param key
     * @param hashKeys
     * @param <HK>
     * @return
     */
    public <HK, HV> Map<HK, HV> hashGet(K key, List<HK> hashKeys) {
        Map<HK, HV> propertiesMap = new HashMap<>();
        List<HV> hashValues = (List<HV>) template.opsForHash().multiGet(key, (Collection<Object>) hashKeys);

        for (int i = 0; i < hashKeys.size(); i++) {
            propertiesMap.put(hashKeys.get(i),hashValues.get(i));
        }
        return propertiesMap;
    }

    /**
     * hash散列所有keys
     * @param key
     * @param <HV>
     * @return
     */
    public <HV> Set<HV> hashKeys(K key) {
        return (Set<HV>) template.opsForHash().keys(key);
    }

    /**
     * hash散列所有values
     * @param key
     * @param <HV>
     * @return
     */
    public <HV> List<HV> hashValues(K key) {
        return (List<HV>) template.opsForHash().values(key);
    }


}
