package com.hyq.cache.redis.core.constants.redisKey;

/**
 * @author nanke
 * @date 2020/3/21 下午4:25
 */
public interface LoginRedisPreKey {

    /**
     * 登录 - 用户缓存
     */
    String loginKey = "login:";

    static String getLoginKey(Long userId) {
        return loginKey + userId;
    }
}
