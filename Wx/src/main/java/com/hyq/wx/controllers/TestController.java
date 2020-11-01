package com.hyq.wx.controllers;

import com.google.gson.GsonBuilder;
import com.hyq.wx.services.WxCpService;
import com.hyq.wx.services.rdto.cp.ClientGroupListRDTO;
import com.hyq.wx.services.repository.WxCpRepository;
import com.hyq.wx.services.repository.wxrequest.ClientGroupRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nanke
 * @date 2020/11/1 4:54 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
@RestController
public class TestController {

    @Resource
    private WxCpService wxCpService;
    @Resource
    private WxCpRepository wxCpRepository;

    @RequestMapping("/test")
    public Object test() {
        return wxCpService.getAccessToken();
    }

    @RequestMapping("/list")
    public Object list() {
        return wxCpRepository.getClientGroupList();
    }

    @RequestMapping("/detail")
    public Object detail() {
        ClientGroupListRDTO clientGroupList = wxCpRepository.getClientGroupList();
        return wxCpRepository.getClientGroupInfo(clientGroupList.getGroupChatList().get(0).getChatId());
    }

    public static void main(String[] args) {

        ClientGroupRequest request = new ClientGroupRequest();
        request.setChatId("xxx");
        String s = new GsonBuilder().create().toJson(request);
        System.out.println(s);


    }
}
