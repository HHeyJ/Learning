package com.hyq.cache.redis.controller;

import com.hyq.cache.redis.core.result.Result;
import com.hyq.cache.redis.core.service.webapplication.LoginService;
import com.hyq.cache.redis.dao.dto.UserDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nanke
 * @date 2020/3/21 下午4:33
 *  1、模拟登录
 */
@RestController
public class LoginController {

    @Resource
    private LoginService loginService;

    @RequestMapping("/login")
    public Result<UserDTO> login() {
        return Result.buildSuccess(loginService.login());
    }
}
