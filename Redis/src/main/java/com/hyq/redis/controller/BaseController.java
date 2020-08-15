package com.hyq.redis.controller;

import com.hyq.redis.core.service.webapplication.LoginService;
import com.hyq.redis.dto.UserDTO;

import javax.annotation.Resource;

/**
 * @author nanke
 * @date 2020/3/21 下午6:02
 */
public class BaseController {

    @Resource
    private LoginService loginService;

    public UserDTO getUserInfo() {

        UserDTO login = loginService.login();
        if (login == null)
            throw new RuntimeException("登录GG");
        return login;
    }
}
