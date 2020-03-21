package com.hyq.cache.redis.controller;

import com.hyq.cache.redis.core.service.webapplication.LoginService;
import com.hyq.cache.redis.dao.dto.UserDTO;

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
