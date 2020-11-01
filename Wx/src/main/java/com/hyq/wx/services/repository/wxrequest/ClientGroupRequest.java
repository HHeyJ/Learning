package com.hyq.wx.services.repository.wxrequest;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author nanke
 * @date 2020/11/1 10:15 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 *
 * <href>https://work.weixin.qq.com/api/doc/90000/90135/92122</href>
 */
@Data
public class ClientGroupRequest {

    @SerializedName("chat_id")
    private String chatId;
}
