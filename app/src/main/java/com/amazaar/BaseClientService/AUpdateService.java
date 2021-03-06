package com.amazaar.BaseClientService;

import android.os.AsyncTask;

import com.amazaar.CommonCode.HttpCaller;
import com.amazaar.CommonCode.ProtoJsonUtil;
import com.amazaar.CommonCode.ProtoSerilizerAndDeserilizer;
import com.amazaar.ServerConfig.ServerUrlManeger;
import com.amazaar.ServerConfig.UrlPathProvider;
import com.google.protobuf.GeneratedMessageV3;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;

public class AUpdateService<Pb extends GeneratedMessageV3> extends AsyncTask<Pb, Void, Pb> {

    private Class<Pb> m_clazz;
    private ProtoSerilizerAndDeserilizer m_serilizer;
    private UrlPathProvider.UrlPathEnum m_pathEnum;

    public AUpdateService(Class<Pb> clazz, UrlPathProvider.UrlPathEnum pathEnum) {
        m_clazz = clazz;
        m_serilizer = new ProtoSerilizerAndDeserilizer();
        m_pathEnum=pathEnum;
    }

    @Override
    protected Pb doInBackground(Pb... workerPbs) {
        ServerUrlManeger urlManeger = new ServerUrlManeger();
        HttpCaller caller = new HttpCaller(RequestMethodEnum.PUT, RequestContentTypeEnum.CONTENT_TYPE_JSON, urlManeger.getServerUrl(m_pathEnum), m_serilizer.getJsonObject(workerPbs[0]));
        try {
            return ProtoJsonUtil.fromJson(caller.execute().toString(), m_clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}