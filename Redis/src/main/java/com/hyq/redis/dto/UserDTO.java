package com.hyq.redis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author nanke
 * @date 2020/3/21 下午4:13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO extends BaseDTO {

    /**
     * 用户id
     */
    private Long userId;
    /**
     * 昵称
     */
    private String nick;
    /**
     * 最近登录时间
     */
    private Date loginTime;
    /**
     * 钱包
     */
    private Integer money;
}
