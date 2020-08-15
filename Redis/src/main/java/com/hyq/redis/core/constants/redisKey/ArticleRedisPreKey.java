package com.hyq.redis.core.constants.redisKey;

/**
 * @author nanke
 * @date 2020/3/19 下午8:10
 */
public interface ArticleRedisPreKey {

    /**
     * 文章合集 - 用户评分倒序
     */
    String articleKey = "article";
    /**
     * 文章合集 - 创建时间倒序
     */
    String articleTimeKey = "articleTime";
    /**
     * 文章投票合集
     */
    String articleFansKey = "articleFans:";
    /**
     * 文章属性
     */
    String articlePropertiesKey = "articleProperties:";

    static String getArticleFansListKey(Long id) {
        return articleFansKey + id;
    }

    static String getArticlePropertiesKey(Long id) {
        return articlePropertiesKey + id;
    }

    /**
     * 根据类型获取文章合集key
     * @param type
     * @return
     */
    static String getArticleKeyByType(Integer type) {
        if (type.equals(1))
            return articleKey;
        if (type.equals(2))
            return articleTimeKey;
        return null;
    }
}
