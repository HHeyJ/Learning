package com.hyq.wx.services.rdto.cp;

import com.google.gson.annotations.SerializedName;
import com.hyq.wx.services.properties.WxCpProperties;
import com.hyq.wx.services.rdto.wx.WxBaseRDTO;
import lombok.Data;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author nanke
 * @date 2020/11/1 10:44 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
@Data
public class ClientGroupListRDTO extends WxBaseRDTO {

    /**
     * 客户群列表
     */
    @SerializedName("group_chat_list")
    private List<GroupChat> groupChatList;

    @Data
    public static class GroupChat {

        /**
         * 客户群ID
         */
        @SerializedName("chat_id")
        private String chatId;

        /**
         * 客户群状态。
         * 0 - 正常
         * 1 - 跟进人离职
         * 2 - 离职继承中
         * 3 - 离职继承完成
         */
        @SerializedName("status")
        private Integer status;
    }

    public static ClientGroupListRDTO fromJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return WxCpProperties.GsonInstance.fromJson(json,ClientGroupListRDTO.class);
    }
}
