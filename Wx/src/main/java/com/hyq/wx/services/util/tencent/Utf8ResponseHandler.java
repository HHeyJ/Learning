package com.hyq.wx.services.util.tencent;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @author nanke
 * @date 2020/11/1 4:47 下午
 * 致终于来到这里的勇敢的人:
 * 永远不要放弃！永远不要对自己失望！永远不要逃走辜负了自己。
 * 永远不要哭啼！永远不要说再见！永远不要说慌来伤害目己。
 */
public class Utf8ResponseHandler implements ResponseHandler<String> {

    public static final ResponseHandler<String> INSTANCE = new Utf8ResponseHandler();

    public Utf8ResponseHandler() {
    }

    public String handleResponse(HttpResponse response) throws IOException {
        StatusLine statusLine = response.getStatusLine();
        HttpEntity entity = response.getEntity();
        if (statusLine.getStatusCode() >= 300) {
            EntityUtils.consume(entity);
            throw new HttpResponseException(statusLine.getStatusCode(), statusLine.getReasonPhrase());
        } else {
            return entity == null ? null : EntityUtils.toString(entity, Consts.UTF_8);
        }
    }

}
