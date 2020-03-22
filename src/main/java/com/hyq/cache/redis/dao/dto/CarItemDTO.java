package com.hyq.cache.redis.dao.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author nanke
 * @date 2020/3/21 下午6:23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarItemDTO extends BaseDTO {

    /**
     * 商品id
     */
    private Long itemId;
    /**
     * 商品数量
     */
    private Integer itemNum;
}
