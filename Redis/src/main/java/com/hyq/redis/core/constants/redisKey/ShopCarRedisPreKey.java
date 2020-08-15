package com.hyq.redis.core.constants.redisKey;

/**
 * @author nanke
 * @date 2020/3/22 下午8:36
 */
public interface ShopCarRedisPreKey {

    String shopCarKey = "shopCar:";

    static String getShopCarKey(Long userId) {
        return shopCarKey + userId;
    }
}
