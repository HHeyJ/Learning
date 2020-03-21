package com.hyq.cache.redis.core.service.webapplication;

import com.hyq.cache.redis.core.common.RedisExecoter;
import com.hyq.cache.redis.core.constants.TimeConstant;
import com.hyq.cache.redis.core.constants.redisKey.LoginRedisPreKey;
import com.hyq.cache.redis.dao.dto.UserDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;

/**
 * @author nanke
 * @date 2020/3/21 下午4:10
 */
@Service
public class LoginService {

    @Resource
    private RedisExecoter redisExecoter;

    private static final String USER_ID = "userId";
    private static final String NICK = "nick";
    private static final String LOGIN_TIME = "loginTime";

    /**
     * 登录服务
     * @return
     */
    public UserDTO login() {

        UserDTO userDTO = simulateUserInfo();
        String cacheKey = LoginRedisPreKey.getLoginKey(userDTO.getUserId());

        Object userId = redisExecoter.hashGet(cacheKey, USER_ID);
        if (userId == null) {
            redisExecoter.hashPut(cacheKey,UserDTO.conver(userDTO), TimeConstant.sevenDaySec);
            return userDTO;
        }

        redisExecoter.hashPut(cacheKey,LOGIN_TIME,userDTO.getLoginTime());
        Map map = redisExecoter.hashGet(cacheKey, Arrays.asList(USER_ID, NICK, LOGIN_TIME));
        Object conver = UserDTO.conver(map, UserDTO.class);
        return conver == null ? null : (UserDTO) conver;
    }








    /**
     * 模拟获取用户信息
     * @return
     */
    private UserDTO simulateUserInfo() {

        Random random = new Random();
        Long userId = Long.valueOf(random.nextInt(100));

        return UserDTO.builder()
                .userId(userId)
                .nick("用户" + userId)
                .loginTime(new Date())
                .build();
    }



}
