package com.hyq.wx.services.constants;


import lombok.experimental.UtilityClass;

/**
 * @author nanke
 * @date 2020/10/30 2:58 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
@UtilityClass
public final class WxConstant {

  /**
   * 向微信发起请求最大重试次数
   */
  Integer maxRetryTimes = 5;

  /**
   * 企业微信路径
   */
  public static class CPPath {

    /**
     * Api前缀
     */
    public static final String ApiPreURL = "https://qyapi.weixin.qq.com";
    /**
     * 获取AccessToken
     */
    public static final String AccessTokenURL = "/cgi-bin/gettoken?corpid=%s&corpsecret=%s";
    /**
     * 获取客户群列表
     */
    public static final String ClientGroupListURL = "/cgi-bin/externalcontact/groupchat/list?access_token=%s";
    /**
     * 获取客户群详情
     */
    public static final String ClientGroupURL = "/cgi-bin/externalcontact/groupchat/get?access_token=%s";



  }
  /**
   * 企业微信配置
   */
  public static class CPConfig {

    /**
     * 企业微信ID
     * TODO hu
     */
    public static String corPid = "ww980116802c55cd75";
    /**
     * 企业微信后台开发者设置的Token
     * TODO hu
     */
    public static String token = "QDG6eK";
    /**
     * 企业微信后台开发者设置的EncodingAESKey
     * TODO hu
     */
    public static final String encodingAesKey = "jWmYm7qr5nMoAUwZRjGtBxmz3KA1tkAj3ykkR6q2B2C";
    /**
     * 企业微信客户联系Secret
     */
    public static final String clientGroupSecret = "3-8w__SCdNWHLYlhhLoMZ6iEiFOVBG9mWfvWriXgANo";
    /**
     * 企业微信AccessToken缓存Key
     */
    public static final String qmTokenKey = "qmTokenKey" + corPid;
  }

}
