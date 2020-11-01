package com.hyq.wx.services;

import com.hyq.wx.services.constants.WxConstant;
import com.hyq.wx.services.util.tencent.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.Serializable;

/**
 * 微信API的Service.
 *
 * @author chanjaster
 */
@Service
@Slf4j
public class WxCpService implements Serializable {

    /**
     * 验证推送过来的消息的正确性
     * 详情请见: http://mp.weixin.qq.com/wiki/index.php?title=验证消息真实性
     * @param msgSignature 消息签名
     * @param timestamp    时间戳
     * @param nonce        随机数
     * @param data         微信传输过来的数据，有可能是echoStr，有可能是xml消息
     * @return the boolean
     */
    public boolean checkSignature(String msgSignature, String timestamp, String nonce, String data) {
        try {
            return SHA1.gen(WxConstant.CPConfig.token, timestamp, nonce, data)
                    .equals(msgSignature);
        } catch (Exception e) {
            log.error("微信回调签名验证失败", e);
        }
        return false;
    }

    /**
     * 获取AssertToken
     * 定时任务刷新,此处直接从缓存中获取
     * @return
     */
    public String getAccessToken() {
        // TODO hu
        return "2ZjcVztg5d8UAvL1ikANzKyii0P6NWgbuu9cpD0Ii8kg8pHmMF-Vl0C08Y-uSKB_YGz_UsKXq3AlDyX2N8YGqAmG_ech6fi-TFPvRI5Eu0VhfZsJu3Ynwjvyvct3QDPp2ldDiVQh5ZnmE7cJsE9n49BOHV64ssB3HsFzI1rDMwvP1T0JZ4WcLipGcaJvFo2InBAEXAE25iKNIeQwTWtmGQ";

    }

}
