package com.hyq.wx.services.repository;

import com.hyq.wx.services.WxCpService;
import com.hyq.wx.services.constants.WxConstant;
import com.hyq.wx.services.properties.WxCpProperties;
import com.hyq.wx.services.rdto.cp.AccessTokenRDTO;
import com.hyq.wx.services.rdto.cp.ClientGroupListRDTO;
import com.hyq.wx.services.rdto.cp.ClientGroupRDTO;
import com.hyq.wx.services.repository.wxrequest.ClientGroupListRequest;
import com.hyq.wx.services.repository.wxrequest.ClientGroupRequest;
import com.hyq.wx.services.util.http.WxHttpClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @author nanke
 * @date 2020/10/31 4:38 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
@Component
@Slf4j
public class WxCpRepository {

    @Resource
    private WxCpService wxCpService;

    /**
     * 禁止使用
     * HttpApi获取企业微信客户联系AsscessToken
     */
    public AccessTokenRDTO getAccessToken() throws UnsupportedEncodingException {
        // 获取客户联系AsscessToken的HttpURL
        String requestURL = WxCpProperties.getRequestURL(String.format(WxConstant.CPPath.AccessTokenURL,
                URLEncoder.encode(WxConstant.CPConfig.corPid,"UTF-8"), URLEncoder.encode(WxConstant.CPConfig.clientGroupSecret,"UTF-8")));
        String httpResult = WxHttpClient.get(requestURL);

        return AccessTokenRDTO.fromJson(httpResult);
    }

    /**
     * 获取客户群列表
     * 该接口用于获取配置过客户群管理的客户群列表。
     */
    public ClientGroupListRDTO getClientGroupList() {

        // accessToken从缓存获取
        String accessToken = wxCpService.getAccessToken();
        // 完整请求路径
        String requestURL = WxCpProperties.getRequestURL(String.format(WxConstant.CPPath.ClientGroupListURL, accessToken));
        // json参数
        ClientGroupListRequest request = new ClientGroupListRequest();
        String json = WxCpProperties.GsonInstance.toJson(request);
        String httpResult = WxHttpClient.post(requestURL, json);
        return ClientGroupListRDTO.fromJson(httpResult);
    }

    /**
     * 获取客户群详情
     * 通过客户群ID，获取详情。包括群名、群成员列表、群成员入群时间、入群方式。（客户群是由具有客户群使用权限的成员创建的外部群）
     * @param chatId 群ID
     * @return
     */
    public ClientGroupRDTO getClientGroupInfo(String chatId) {
        // accessToken从缓存获取
        String accessToken = wxCpService.getAccessToken();
        // 完整请求路径
        String requestURL = WxCpProperties.getRequestURL(String.format(WxConstant.CPPath.ClientGroupURL, accessToken));
        // json参数
        ClientGroupRequest request = new ClientGroupRequest();
        request.setChatId(chatId);
        String json = WxCpProperties.GsonInstance.toJson(request);
        // 发起网络请求
        String httpResult = WxHttpClient.post(requestURL, json);
        return ClientGroupRDTO.fromJson(httpResult);
    }

}
