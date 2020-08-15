package com.hyq.redis.core.enums;

/**
 * @author nanke
 * @date 2020/3/19 下午8:29
 */
public enum  ErrorEnum {

    DEFAULT_ERROR(100,"默认错误"),
    LIKE_FAIL(101,"点赞失败"),
    ;

    /**
     * 错误code
     */
    public Integer code;
    /**
     * 错误描述
     */
    public String message;

    ErrorEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
