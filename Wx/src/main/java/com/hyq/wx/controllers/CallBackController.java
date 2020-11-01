package com.hyq.wx.controllers;

import com.hyq.wx.services.WxCpService;
import com.hyq.wx.services.common.message.WxCpXmlMessage;
import com.hyq.wx.services.constants.WxConstant;
import com.hyq.wx.services.util.tencent.WxEncryDecryUtil;
import org.apache.commons.io.IOUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * @author nanke
 * @date 2020/10/30 4:28 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
@RestController
@RequestMapping("/qm/wx/callBack")
public class CallBackController {

    @Resource
    private WxCpService wxCpService;

    @RequestMapping("/cp")
    public void cpCallBack(HttpServletRequest request, HttpServletResponse response) throws IOException {

        String msgSignature = request.getParameter("msg_signature");
        String nonce = request.getParameter("nonce");
        String timestamp = request.getParameter("timestamp");
        String echostr = request.getParameter("echostr");

        WxEncryDecryUtil cryptUtil = new WxEncryDecryUtil(WxConstant.CPConfig.token,WxConstant.CPConfig.encodingAesKey,WxConstant.CPConfig.corPid);
        if (!StringUtils.isEmpty(echostr)) {
            // 微信回调验签
            if (wxCpService.checkSignature(msgSignature, timestamp, nonce, echostr)) {
                // 消息签名不正确，说明不是公众平台发过来的消息
                response.getWriter().println("非法请求");
                return;
            }
            String plainText = cryptUtil.decrypt(echostr);
            // 回显请求将解密明文返回
            response.getWriter().println(plainText);
            return;
        }
        // 传输内容
        ServletInputStream is = request.getInputStream();
        String transferContent = IOUtils.toString(is, StandardCharsets.UTF_8);
        // XML密文
        String xmlContent = WxEncryDecryUtil.extractEncryptPart(transferContent);
        // 微信密文验签
        if (!wxCpService.checkSignature(msgSignature, timestamp, nonce, xmlContent)) {
            // 消息签名不正确，说明不是公众平台发过来的消息
            response.getWriter().println("非法请求");
            return;
        }
        // XML明文
        String content = cryptUtil.decrypt(xmlContent);
        // 解析XML
        WxCpXmlMessage wxCpXmlMessage = WxCpXmlMessage.fromXml(content);
        /**
         * 响应微信 Response=200,否则发起重试3次
         * 自身业务考虑是否幂等
         */
        // TODO 选择消息处理器
        String event = wxCpXmlMessage.getEvent();
        String msgType = wxCpXmlMessage.getMsgType();
        // 返回
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(HttpServletResponse.SC_OK);
    }



}
