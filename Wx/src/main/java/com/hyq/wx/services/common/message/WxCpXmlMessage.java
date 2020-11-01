package com.hyq.wx.services.common.message;

import com.hyq.wx.services.util.tencent.XStreamCDataConverter;
import com.hyq.wx.services.util.tencent.XStreamTransformer;
import com.hyq.wx.services.util.tencent.XmlUtils;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Map;

/**
 * 微信推送过来的消息，也是同步回复给用户的消息，xml格式
 * 相关字段的解释看微信开发者文档：
 * https://work.weixin.qq.com/api/doc#12973
 * https://work.weixin.qq.com/api/doc#12974
 */
@Data
@Slf4j
@XStreamAlias("xml")
public class WxCpXmlMessage implements Serializable {
  private static final long serialVersionUID = -1042994982179476410L;

  /**
   * 使用dom4j解析的存放所有xml属性和值的map.
   */
  private Map<String, Object> allFieldsMap;

  /**
   * 企业微信CorpID
   */
  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String toUserName;

  /**
   * 此事件该值固定为sys，表示该消息由系统生成
   */
  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String fromUserName;

  /**
   * 消息创建时间 （unix时间戳）
   */
  @XStreamAlias("CreateTime")
  private Long createTime;

  /**
   * 消息的类型，此时固定为event
   */
  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String msgType;

  /**
   * 事件的类型，此时固定为 change_external_chat
   */
  @XStreamAlias("Event")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String event;

  /**
   * 群ID
   */
  @XStreamAlias("ChatId")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String chatId;

  public static WxCpXmlMessage fromXml(String xml) {
    //修改微信变态的消息内容格式，方便解析
    xml = xml.replace("</PicList><PicList>", "");
    final WxCpXmlMessage xmlMessage = XStreamTransformer.fromXml(WxCpXmlMessage.class, xml);
    xmlMessage.setAllFieldsMap(XmlUtils.xml2Map(xml));
    return xmlMessage;
  }
}
