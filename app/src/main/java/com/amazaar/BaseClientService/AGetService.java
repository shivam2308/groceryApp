package com.amazaar.BaseClientService;

import android.os.AsyncTask;

import com.amazaar.CommonCode.HttpCaller;
import com.amazaar.CommonCode.ProtoJsonUtil;
import com.amazaar.ServerConfig.GetUrlMaker;
import com.amazaar.ServerConfig.ServerUrlManeger;
import com.amazaar.ServerConfig.UrlPathProvider;
import com.google.protobuf.GeneratedMessageV3;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;

public class AGetService<Pb extends GeneratedMessageV3> extends AsyncTask<String, Void, Pb> {

    private Class<Pb> m_clazz;
    private UrlPathProvider.UrlPathEnum m_pathEnum;

    public AGetService(Class<Pb> clazz, UrlPathProvider.UrlPathEnum pathEnum) {
        m_clazz = clazz;
        m_pathEnum = pathEnum;
    }

    @Override
    protected Pb doInBackground(String... id) {
        ServerUrlManeger urlManeger = new ServerUrlManeger();
        HttpCaller caller = new HttpCaller(RequestMethodEnum.GET, RequestContentTypeEnum.CONTENT_TYPE_JSON, GetUrlMaker.getUrlWIthQuery(urlManeger.getServerUrl(m_pathEnum), id[0]), null);
        try {
            return ProtoJsonUtil.fromJson(caller.execute().toString(), m_clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
