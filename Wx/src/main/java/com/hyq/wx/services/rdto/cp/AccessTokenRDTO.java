package com.hyq.wx.services.rdto.cp;

import com.google.gson.annotations.SerializedName;
import com.hyq.wx.services.properties.WxCpProperties;
import com.hyq.wx.services.rdto.wx.WxBaseRDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.util.StringUtils;

/**
 * @author nanke
 * @date 2020/10/31 5:43 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccessTokenRDTO extends WxBaseRDTO {

    /**
     * AccessToken
     */
    @SerializedName("access_token")
    private String accessToken;
    /**
     * 剩余过期时间
     */
    @SerializedName("expires_in")
    private Integer expiresIn;

    public static AccessTokenRDTO fromJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return null;
        }
        return WxCpProperties.GsonInstance.fromJson(json,AccessTokenRDTO.class);
    }
}
