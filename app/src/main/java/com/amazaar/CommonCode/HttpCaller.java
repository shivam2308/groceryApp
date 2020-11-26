package com.amazaar.CommonCode;

import android.util.Log;

import com.google.gson.JsonObject;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;
import com.prod.basic.common.httpReqAndResp.HttpReqHandler;

import java.util.concurrent.Callable;


public class HttpCaller extends HttpReqHandler {

    public HttpCaller(RequestMethodEnum method, RequestContentTypeEnum contentType, String url, JsonObject content) {
        super(method, contentType, url, content);
        if (content != null) {
            Log.d("Payload", content.toString());
        }
    }


    public JsonObject execute() {
        Callable<JsonObject> callable = new Callable<JsonObject>() {
            @Override
            public JsonObject call() throws Exception {
                return docall();
            }
        };
        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
