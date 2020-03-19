package com.hyq.cache.redis.core.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author nanke
 * @date 2020/3/19 下午8:22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDTO implements Serializable {

    /**
     * 文章id
     */
    private Long id;
    /**
     * 文章标题
     */
    private String tile;
    /**
     * 创建时间
     */
    private Date creatTime;
}
