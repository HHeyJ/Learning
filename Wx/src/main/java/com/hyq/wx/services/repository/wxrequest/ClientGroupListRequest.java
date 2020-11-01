package com.hyq.wx.services.repository.wxrequest;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.util.List;

/**
 * @author nanke
 * @date 2020/11/1 10:37 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 *
 * <href>https://work.weixin.qq.com/api/doc/90000/90135/92120</href>
 */
@Data
public class ClientGroupListRequest {

    /**
     * 群状态过滤 - 非必填
     * 0 - 所有列表 - 默认值
     * 1 - 离职待继承
     * 2 - 离职继承中
     * 3 - 离职继承完成
     */
    @SerializedName("status_filter")
    private Integer statusFilter;

    /**
     * 群主过滤 - 非必填
     * 如果不填，表示获取全部群主的数据
     */
    @SerializedName("owner_filter")
    private ownerFilter ownerFilter;

    /**
     * 分页偏移量 - 默认0 - 非必填
     */
    @SerializedName("offset")
    private Integer offset = 0;

    /**
     * 分页请求的数据量,取值范围 1 ~ 1000
     */
    @SerializedName("limit")
    private Integer limit = 1000;

    @Data
    public static class ownerFilter {

        /**
         * 用户ID列表。最多100个
         */
        @SerializedName("userid_list")
        private List<String> userIdList;

        /**
         * 	部门ID列表。最多100个
         */
        @SerializedName("partyid_list")
        private List<Integer> partyIdList;
    }
}
