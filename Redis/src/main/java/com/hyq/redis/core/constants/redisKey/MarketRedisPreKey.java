package com.hyq.redis.core.constants.redisKey;

/**
 * @author nanke
 * @date 2020/3/27 下午8:47
 */
public interface MarketRedisPreKey {

    String userKey = "user:";

    String parcelKey = "parcel:";

    String marketKey = "market";

    static String getUserKey(Long userId) {
        return userKey + userId;
    }

    static String getParcelKey(Long userId) {
        return parcelKey + userId;
    }
}
