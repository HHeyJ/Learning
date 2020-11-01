package com.hyq.wx.controllers;

import com.hyq.wx.services.WxCpService;
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

    @RequestMapping("/test")
    public Object test() {
        return wxCpService.getAccessToken();
    }
}
