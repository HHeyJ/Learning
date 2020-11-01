package com.hyq.wx.services.rdto.wx;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

/**
 * @author nanke
 * @date 2020/10/31 5:14 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
@Data
public class WxBaseRDTO {

    @SerializedName("errcode")
    protected Long errCode;

    @SerializedName("errmsg")
    protected String errMsg;

    /**
     * 请求是否成功
     * @return
     */
    public boolean success() {
        return getErrCode() == 0;
    }
}
