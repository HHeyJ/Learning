package com.hyq.redis.controller;

import com.hyq.redis.core.result.Result;
import com.hyq.redis.core.service.trans.MarketService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author nanke
 * @date 2020/3/27 下午8:44
 */
@RestController
public class MarketController extends BaseController {

    @Resource
    private MarketService marketService;

    @RequestMapping("putItem")
    public Result<String> putItem() {

        boolean item1 = marketService.putItemMarket(2L, "item4", 100L);
        if (item1)
            return Result.buildSuccess("挂售成功～");
        return Result.buildFail("挂售失败～");
    }

    @RequestMapping("buyItem")
    public Result<String> buyItem() {

        boolean item1 = marketService.buyItemMarket(1L, 2L,"item4");
        if (item1)
            return Result.buildSuccess("购买成功～");
        return Result.buildFail("购买失败～");
    }

    @RequestMapping("init")
    public void init() {
        marketService.initData();
    }
}
