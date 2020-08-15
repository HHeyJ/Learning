package com.hyq.redis.controller;

import com.hyq.redis.core.result.Result;
import com.hyq.redis.core.service.shopcar.ShopCarService;
import com.hyq.redis.dto.CarItemDTO;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author nanke
 * @date 2020/3/22 下午8:35
 * 1、购物车添加商品（模拟登录）
 * 2、购物车某商品数量 -1
 * 3、查询购物车所有商品
 */
@RestController
public class ShopCarController extends BaseController {

    @Resource
    private ShopCarService shopCarService;

    @RequestMapping("addCarItem")
    public Result<CarItemDTO> addCarItem() {
        CarItemDTO carItemDTO = shopCarService.addItem(getUserInfo().getUserId(), 1L);
        return Result.buildSuccess(carItemDTO);
    }

    @RequestMapping("deleteCarItem")
    public Result<CarItemDTO> deleteCarItem() {
        CarItemDTO carItemDTO = shopCarService.removeItem(getUserInfo().getUserId(), 1L);
        return Result.buildSuccess(carItemDTO);
    }

    @RequestMapping("queryShopCar")
    public Result<List<CarItemDTO>> queryShopCar() {
        List<CarItemDTO> carItemDTOS = shopCarService.queryShopCar(getUserInfo().getUserId());
        return Result.buildSuccess(carItemDTOS);
    }


}
