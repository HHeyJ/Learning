package com.hyq.wx.services.rdto.cp;

import com.google.gson.GsonBuilder;
import com.google.gson.annotations.SerializedName;
import com.hyq.wx.services.rdto.wx.WxBaseRDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * @author nanke
 * @date 2020/10/31 4:39 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class ClientGroupRDTO extends WxBaseRDTO {

    /**
     * 微信客户群详情
     */
    @SerializedName("group_chat")
    private GroupChatRDTO groupChatRDTO;

    /**
     * 客户群模型
     */
    @Data
    public static class GroupChatRDTO {

        /**
         * 客户群ID
         */
        @SerializedName("chat_id")
        private String chatId;

        /**
         * 群名
         */
        @SerializedName("name")
        private String name;

        /**
         * 群主ID
         */
        @SerializedName("owner")
        private String owner;

        /**
         * 群的创建时间
         */
        @SerializedName("create_time")
        private String createTime;

        /**
         * 群公告
         */
        @SerializedName("notice")
        private String notice;

        /**
         * 群成员列表
         */
        @SerializedName("member_list")
        private List<GroupUserRDTO> userRDTOS;
    }

    /**
     * 客户群用户模型
     */
    @Data
    public static class GroupUserRDTO {

        /**
         * 群成员Id
         */
        @SerializedName("userid")
        private String userId;

        /**
         * 成员类型
         * 1 - 企业成员
         * 2 - 外部联系人
         */
        @SerializedName("type")
        private String type;

        /**
         * 入群时间
         */
        @SerializedName("join_time")
        private String joinTime;

        /**
         * 入群方式。
         * 1 - 由成员邀请入群（直接邀请入群）
         * 2 - 由成员邀请入群（通过邀请链接入群）
         * 3 - 通过扫描群二维码入群
         */
        @SerializedName("join_scene")
        private String joinScene;

        /**
         * 外部联系人在微信开放平台的唯一身份标识（微信unionid）,通过此字段企业可将外部联系人与公众号/小程序用户关联起来。
         * 仅当群成员类型是微信用户（包括企业成员未添加好友），且企业或第三方服务商绑定了微信开发者ID有此字段。
         * 查看绑定方法 <href>https://work.weixin.qq.com/api/doc/90000/90135/92114#%E5%A6%82%E4%BD%95%E7%BB%91%E5%AE%9A%E5%BE%AE%E4%BF%A1%E5%BC%80%E5%8F%91%E8%80%85ID</href>
         */
        @SerializedName("unionid")
        private String unionId;

    }

    public static ClientGroupRDTO fromJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return new GsonBuilder().create().fromJson(json,ClientGroupRDTO.class);
    }


}
