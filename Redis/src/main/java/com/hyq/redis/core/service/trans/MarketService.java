package com.hyq.redis.core.service.trans;

import com.hyq.redis.core.common.RedisExecoter;
import com.hyq.redis.core.constants.redisKey.MarketRedisPreKey;
import com.hyq.redis.dto.UserDTO;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;

/**
 * @author nanke
 * @date 2020/3/27 下午8:45
 */
@Service
public class MarketService {

    @Resource
    private RedisExecoter redisExecoter;

    /**
     * 挂在商品到集市
     * @param userId
     * @param itemName
     * @param salePrice
     */
    public boolean putItemMarket(Long userId, String itemName, Long salePrice) {

        String parcelKey = MarketRedisPreKey.getParcelKey(userId);
        // 就这样吧
        if (!redisExecoter.sHasMember(parcelKey, itemName))
            return false;

        SessionCallback<Object> callback = new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                // 监控
                operations.watch(Arrays.asList(parcelKey, MarketRedisPreKey.marketKey));
                operations.multi();
                // 包裹扣除
                redisExecoter.sRemove(parcelKey, itemName);
                // 市场添加
                redisExecoter.zSet(MarketRedisPreKey.marketKey, itemName, salePrice, null);
                // 事物提交
                return operations.exec();
            }
        };
        if (redisExecoter.transExec(callback).size() == 0)
            return false;
        return true;
    }

    /**
     * 从集市购买商品
     * @param buyUserId
     * @param sellUserId
     * @param itemName
     * @return
     */
    public boolean buyItemMarket(Long buyUserId, Long sellUserId, String itemName) {

        String buyKey = MarketRedisPreKey.getUserKey(buyUserId);
        String sellKey = MarketRedisPreKey.getUserKey(sellUserId);
        // 钱不够
        Integer money = (Integer) redisExecoter.hashGet(buyKey, UserDTO.conver(UserDTO.class)).get("money");
        //
        Long salePrice = redisExecoter.zGetScore(MarketRedisPreKey.marketKey, itemName);
        if (money < salePrice)
            return false;

        SessionCallback<Object> callback = new SessionCallback<Object>() {
            @Override
            public Object execute(RedisOperations operations) throws DataAccessException {
                // 监控
                operations.watch(Arrays.asList(buyKey, MarketRedisPreKey.marketKey));
                operations.multi();
                // 卖家加钱
                redisExecoter.hashIncr(sellKey,"money",salePrice);
                // 买家扣钱
                redisExecoter.hashIncr(buyKey,"money",-salePrice);
                // 市场下架
                redisExecoter.zRemove(MarketRedisPreKey.marketKey,itemName);
                // 加入买家包裹
                redisExecoter.sSet(MarketRedisPreKey.getParcelKey(buyUserId),itemName,null);
                // 事物提交
                return operations.exec();
            }
        };
        if (redisExecoter.transExec(callback).size() == 0)
            return false;
        return true;
    }















    /**
     * 初始化数据
     */
    public void initData() {

        UserDTO one = UserDTO.builder().userId(1L).money(100).build();
        // 用户
        redisExecoter.hashPut(MarketRedisPreKey.getUserKey(1L),UserDTO.conver(one),null);
        // 包裹
        redisExecoter.sSet(MarketRedisPreKey.getParcelKey(1L), "item1",null);
        redisExecoter.sSet(MarketRedisPreKey.getParcelKey(1L), "item2",null);
        redisExecoter.sSet(MarketRedisPreKey.getParcelKey(1L), "item3",null);

        UserDTO two = UserDTO.builder().userId(2L).money(100).build();
        // 用户
        redisExecoter.hashPut(MarketRedisPreKey.getUserKey(2L),UserDTO.conver(one),null);
        // 包裹
        redisExecoter.sSet(MarketRedisPreKey.getParcelKey(2L), "item4",null);
        redisExecoter.sSet(MarketRedisPreKey.getParcelKey(2L), "item5",null);
        redisExecoter.sSet(MarketRedisPreKey.getParcelKey(2L), "item6",null);

    }


}
