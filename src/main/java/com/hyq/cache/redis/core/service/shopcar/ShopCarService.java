package com.hyq.cache.redis.core.service.shopcar;

import com.alibaba.fastjson.JSON;
import com.hyq.cache.redis.core.common.RedisExecoter;
import com.hyq.cache.redis.core.constants.redisKey.ShopCarRedisPreKey;
import com.hyq.cache.redis.dao.dto.CarItemDTO;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author nanke
 * @date 2020/3/22 下午8:35
 * TODO 所有判断均存在并发风险
 */
@Service
public class ShopCarService {

    @Resource
    private RedisExecoter redisExecoter;

    /**
     * 添加商品
     * 某商品数量 +1
     * @param userId
     * @param itemId
     * @return
     */
    public CarItemDTO addItem(Long userId, Long itemId) {

        String cacheKey = ShopCarRedisPreKey.getShopCarKey(userId);
        String carItem = (String) redisExecoter.hashGet(cacheKey, itemId.toString());
        if (StringUtils.isEmpty(carItem)) {
            CarItemDTO build = CarItemDTO.builder().itemId(itemId).itemNum(1).build();
            redisExecoter.hashPut(cacheKey,itemId.toString(), JSON.toJSONString(build));
            return build;
        }

        carItem = (String) redisExecoter.hashGet(cacheKey, itemId.toString());
        CarItemDTO carItemDTO = JSON.parseObject(carItem, CarItemDTO.class);
        carItemDTO.setItemNum(carItemDTO.getItemNum() + 1);
        redisExecoter.hashPut(cacheKey,itemId.toString(),JSON.toJSONString(carItemDTO));
        return carItemDTO;
    }

    /**
     * 某商品数量 -1
     * @param userId
     * @param itemId
     * @return
     */
    public CarItemDTO removeItem(Long userId, Long itemId) {

        String cacheKey = ShopCarRedisPreKey.getShopCarKey(userId);
        if (!redisExecoter.hashKey(cacheKey, itemId.toString()))
            return null;
        String carItem = (String) redisExecoter.hashGet(cacheKey, itemId.toString());
        CarItemDTO carItemDTO = JSON.parseObject(carItem,CarItemDTO.class);
        carItemDTO.setItemNum(carItemDTO.getItemNum() - 1);
        if (carItemDTO.getItemNum() <= 0) {
            redisExecoter.deleteKey(cacheKey,itemId.toString());
            return carItemDTO;
        }
        redisExecoter.hashPut(cacheKey,itemId.toString(),JSON.toJSONString(carItemDTO));
        return carItemDTO;
    }

    public List<CarItemDTO> queryShopCar(Long userId) {

        List<CarItemDTO> result = new ArrayList<>();
        String cacheKey = ShopCarRedisPreKey.getShopCarKey(userId);
        if (!redisExecoter.hasKey(cacheKey))
            return result;

        List<String> items = redisExecoter.hashValues(cacheKey);
        items.forEach(item -> result.add(JSON.parseObject(item,CarItemDTO.class)));
        return result;
    }

}
