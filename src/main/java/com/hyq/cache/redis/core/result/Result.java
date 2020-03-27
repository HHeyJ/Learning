package com.hyq.cache.redis.core.result;

import com.hyq.cache.redis.core.enums.ErrorEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * @author nanke
 * @date 2020/3/19 下午8:27
 */
@Data
public class Result<T> implements Serializable {

    /**
     * 状态
     */
    private boolean status;
    /**
     * 错误消息
     */
    private String message;
    /**
     * 返回值
     */
    private int code;
    /**
     * 结果对象
     */
    private T entry;
    /**
     * 分页总数
     */
    private int count;

    public static <T> Result<T> buildSuccess(T entry, int count) {
        Result<T> result = new Result<>();
        result.setStatus(true);
        result.setEntry(entry);
        result.setCount(count);
        return result;
    }

    public static Result buildSuccess() {
        return buildSuccess(null,0);
    }

    public static <T> Result<T> buildSuccess(T entry) {
        return buildSuccess(entry,0);
    }

    public static <T> Result<T> buildFail(ErrorEnum error) {
        Result<T> result = new Result<>();
        result.setStatus(false);
        result.setMessage(error.message);
        result.setCode(error.code);
        return result;
    }

    public static <T> Result<T> buildFail(String error) {
        Result<T> result = new Result<>();
        result.setStatus(false);
        result.setMessage(error);
        result.setCode(0);
        return result;
    }
}
