package com.hyq.cache.redis.controller;

import com.hyq.cache.redis.core.enums.ErrorEnum;
import com.hyq.cache.redis.core.result.Result;
import com.hyq.cache.redis.core.service.article.ArticleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @author nanke
 * @date 2020/3/19 下午3:04
 * @description 文章相关C端接口
 *  1、新建文章
 *  2、点赞分数倒序查询
 *  3、创建时间倒序查询
 *  4、点赞
 *  5、自动生成文章
 */
@RestController
public class ArticleController {

    @Resource
    private ArticleService articleService;

    @RequestMapping("/creatArticle")
    public Result<Long> creatArticle() {

        Long id = articleService.creatArticle();
        return Result.buildSuccess(id);
    }

    @RequestMapping("/articleByScore")
    public Result<List<Map<Object,Object>>> articleByScore(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {

        return Result.buildSuccess(articleService.queryAriticle(1,pageNo,pageSize));
    }

    @RequestMapping("/articleByTime")
    public Result<List<Map<Object,Object>>> articleByTime(@RequestParam Integer pageNo, @RequestParam Integer pageSize) {

        return Result.buildSuccess(articleService.queryAriticle(2,pageNo,pageSize));
    }

    @RequestMapping("/like")
    public Result<Boolean> likeArticle(@RequestParam Long cuserId, @RequestParam Long articleId) {

        Boolean aBoolean = articleService.likeArticle(cuserId, articleId);
        return aBoolean ? Result.buildSuccess(true) : Result.buildFail(ErrorEnum.LIKE_FAIL);
    }


    /**
     * 自动生成文章
     * @param num
     * @return
     */
    @RequestMapping("/generateArticle")
    public Object generateArticle(@RequestParam Integer num) {
        for (int i = 0; i < num; i++) {
            creatArticle();
        }
        return Result.buildSuccess();
    }
}
