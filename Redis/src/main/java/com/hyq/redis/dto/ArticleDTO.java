package com.hyq.redis.dto;

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
public class ArticleDTO extends BaseDTO implements Serializable {

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

    public static ArticleDTO defaultValue(Long id) {

        return ArticleDTO.builder()
                .id(id)
                .tile("标题" + id)
                .creatTime(new Date())
                .build();
    }
}
