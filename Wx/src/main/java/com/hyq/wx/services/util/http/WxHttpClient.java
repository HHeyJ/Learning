package com.hyq.wx.services.util.http;

import com.hyq.wx.services.util.tencent.Utf8ResponseHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.Consts;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.util.StringUtils;

import java.io.IOException;

/**
 * @author nanke
 * @date 2020/10/30 3:11 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 * <p>
 * 微信进行网络通信类
 */
@Slf4j
public class WxHttpClient {

    private static CloseableHttpClient httpClient;

    static {
        httpClient = HttpClients.createDefault();
    }


    /**
     * 针对所有微信API中的GET请求.
     *
     * @param url        请求接口地址
     * @return 接口响应字符串
     */
    public static String get(String url) {

        HttpGet httpGet = new HttpGet(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000).setConnectTimeout(1000).build();
        httpGet.setConfig(requestConfig);
        // 获取响应
        try (CloseableHttpResponse response = httpClient.execute(httpGet)) {
            String responseContent = new BasicResponseHandler().handleResponse(response);
            // TODO hu 判断错误码
            return responseContent;
        } catch (IOException e) {

        } finally {
            httpGet.releaseConnection();
        }
        throw new RuntimeException();
    }

    /**
     * 针对所有微信API中的POST请求.
     *
     * @param json 请求参数json值
     * @param url      请求接口地址
     * @return 接口响应字符串
     */
    public static String post(String url, String json) {

        HttpPost httpPost = new HttpPost(url);
        // 设置超时时间
        RequestConfig requestConfig = RequestConfig.custom().setConnectionRequestTimeout(1000)
                .setSocketTimeout(1000).setConnectTimeout(1000).build();
        httpPost.setConfig(requestConfig);

        if (!StringUtils.isEmpty(json)) {
            StringEntity entity = new StringEntity(json, Consts.UTF_8);
            entity.setContentType("application/json; charset=utf-8");
            httpPost.setEntity(entity);
        }

        try (CloseableHttpResponse response = httpClient.execute(httpPost)) {

            String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
            // TODO hu 判断错误码
            return responseContent;
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        throw new RuntimeException();
    }
}
