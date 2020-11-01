package com.hyq.wx.services.properties;

import com.google.common.base.CharMatcher;
import com.google.common.io.BaseEncoding;
import com.hyq.wx.services.constants.WxConstant;

/**
 * @author nanke
 * @date 2020/10/30 2:58 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
public class WxCpProperties {

    /**
     * 获取请求URL路径
     * @param path
     * @return
     */
    public static String getRequestURL(String path) {
        return WxConstant.CPPath.ApiPreURL + path;
    }

    /**
     * 获取AES密匙
     * @return
     */
    public static byte[] getAESKey() {
        return BaseEncoding.base64().decode(CharMatcher.whitespace().removeFrom(WxConstant.CPConfig.encodingAesKey));
    }
}
