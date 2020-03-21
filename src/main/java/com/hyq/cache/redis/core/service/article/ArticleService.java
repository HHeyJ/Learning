package com.hyq.cache.redis.core.service.article;

import com.hyq.cache.redis.core.common.RedisExecoter;
import com.hyq.cache.redis.core.constants.RedisPreKey;
import com.hyq.cache.redis.core.constants.TimeConstant;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author nanke
 * @date 2020/3/19 下午9:24
 */
@Service
public class ArticleService {

    @Resource
    private RedisExecoter redisExecoter;

    private static final List<String> hashKeys = Arrays.asList("title", "id", "content");

    /**
     * 新建一篇文章
     * @return
     */
    public Long creatArticle() {
        // TODO Lua脚本设置
        // 假装是拿总数吧🤷
        Long id = redisExecoter.zCount(RedisPreKey.articleKey, Integer.MIN_VALUE, Integer.MAX_VALUE) + 1;
        // 文章评分有序集合
        redisExecoter.zSet(RedisPreKey.articleKey,id,0L,null);
        // 文章创建时间有序集合
        redisExecoter.zSet(RedisPreKey.articleTimeKey,id,System.currentTimeMillis(),null);
        // 文章的投票粉丝列表 - 文章投稿者禁止给自己点赞
        Long cuserId = 1L;
        redisExecoter.sSet(RedisPreKey.getArticleFansListKey(id),cuserId, TimeConstant.sevenDaySec);
        // 循环设置属性
        Map<String,Object> properties = new HashMap<>();
        hashKeys.forEach(hashKey -> properties.put(hashKey,hashKey + "-" + id));
        redisExecoter.hashPut(RedisPreKey.getArticlePropertiesKey(id),properties);
        return id;
    }

    /**
     * 分页查询文章
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<Map<Object,Object>> queryAriticle(Integer type, Integer pageNo, Integer pageSize) {
        // 分页查id
        Set<Long> articleIds = redisExecoter.zGet(RedisPreKey.getArticleKeyByType(type), pageNo, pageSize);
        // 获取散列属性
        List<Map<Object,Object>> result = new ArrayList<>();
        articleIds.forEach(id -> {
            Map<Object, Object> map = redisExecoter.hashGet(RedisPreKey.getArticlePropertiesKey(id), new ArrayList<>(hashKeys));
            result.add(map);
        });
        return result;
    }

    /**
     * 文章点赞
     * @param cuserId
     * @param article
     * @return
     */
    public Boolean likeArticle(Long cuserId, Long article) {

        // 文章创建是否超过一周
        Long creatTime = redisExecoter.zGetScore(RedisPreKey.articleTimeKey, article);
        if (System.currentTimeMillis() - creatTime > TimeConstant.sevenDaySec * 1000L)
            return false;
        // 文章点赞人添加
        Long aLong = redisExecoter.sSet(RedisPreKey.getArticleFansListKey(article), cuserId, TimeConstant.sevenDaySec);
        if (!aLong.equals(0L))
            // 文章评分列表成绩 +1
            redisExecoter.zIncrby(RedisPreKey.articleKey,article,1L);
        return !aLong.equals(0L);
    }


}
