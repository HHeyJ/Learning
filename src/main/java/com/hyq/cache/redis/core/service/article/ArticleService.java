package com.hyq.cache.redis.core.service.article;

import com.hyq.cache.redis.core.common.RedisExecoter;
import com.hyq.cache.redis.core.constants.redisKey.ArticleRedisPreKey;
import com.hyq.cache.redis.core.constants.TimeConstant;
import com.hyq.cache.redis.dao.dto.ArticleDTO;
import com.hyq.cache.redis.dao.dto.BaseDTO;
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

    /**
     * 新建一篇文章
     * @return
     */
    public ArticleDTO creatArticle(Long userId) {
        // TODO Lua脚本设置
        // 假装是拿总数吧🤷
        Long id = redisExecoter.zCount(ArticleRedisPreKey.articleKey, Integer.MIN_VALUE, Integer.MAX_VALUE) + 1;
        // 文章评分有序集合
        redisExecoter.zSet(ArticleRedisPreKey.articleKey,id,0L,null);
        // 文章创建时间有序集合
        redisExecoter.zSet(ArticleRedisPreKey.articleTimeKey,id,System.currentTimeMillis(),null);
        // 文章的投票粉丝列表 - 文章投稿者禁止给自己点赞
        redisExecoter.sSet(ArticleRedisPreKey.getArticleFansListKey(id),userId, TimeConstant.sevenDaySec);
        // 循环设置属性
        ArticleDTO articleDTO = ArticleDTO.defaultValue(id);
        HashMap<String, Object> conver = ArticleDTO.conver(articleDTO);
        redisExecoter.hashPut(ArticleRedisPreKey.getArticlePropertiesKey(id),conver,null);
        return articleDTO;
    }

    /**
     * 分页查询文章
     * @param type
     * @param pageNo
     * @param pageSize
     * @return
     */
    public List<ArticleDTO> queryAriticle(Integer type, Integer pageNo, Integer pageSize) {
        String cacheKey = ArticleRedisPreKey.getArticleKeyByType(type);
        // 分页查id
        Set<Long> articleIds = redisExecoter.zGet(cacheKey, pageNo, pageSize);
        // 获取散列属性
        List<ArticleDTO> result = new ArrayList<>();
        articleIds.forEach(id -> {

            Map<String, Object> map = redisExecoter.hashGet(ArticleRedisPreKey.getArticlePropertiesKey(id), BaseDTO.conver(ArticleDTO.class));
            Object conver = ArticleDTO.conver(map, ArticleDTO.class);
            if (conver != null)
                result.add((ArticleDTO) conver);
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
        Long creatTime = redisExecoter.zGetScore(ArticleRedisPreKey.articleTimeKey, article);
        if (System.currentTimeMillis() - creatTime > TimeConstant.sevenDaySec * 1000L)
            return false;
        // 文章点赞人添加
        Long aLong = redisExecoter.sSet(ArticleRedisPreKey.getArticleFansListKey(article), cuserId, TimeConstant.sevenDaySec);
        if (!aLong.equals(0L))
            // 文章评分列表成绩 +1
            redisExecoter.zIncrby(ArticleRedisPreKey.articleKey,article,1L);
        return !aLong.equals(0L);
    }


}
