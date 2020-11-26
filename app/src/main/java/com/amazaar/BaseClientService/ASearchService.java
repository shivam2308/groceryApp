package com.amazaar.BaseClientService;

import android.os.AsyncTask;

import com.amazaar.CommonCode.HttpCaller;
import com.amazaar.CommonCode.ProtoJsonUtil;
import com.amazaar.CommonCode.ProtoSerilizerAndDeserilizer;
import com.amazaar.CommonCode.URlEncoder;
import com.amazaar.ServerConfig.GetUrlMaker;
import com.amazaar.ServerConfig.ServerUrlManeger;
import com.amazaar.ServerConfig.UrlPathProvider;
import com.google.protobuf.GeneratedMessageV3;
import com.prod.basic.common.httpCommon.Enums.RequestContentTypeEnum;
import com.prod.basic.common.httpCommon.Enums.RequestMethodEnum;

import java.io.IOException;

public class ASearchService<LReq extends GeneratedMessageV3,LResp extends GeneratedMessageV3> extends AsyncTask<LReq, Void, LResp> {

    private Class<LResp> m_clazz;
    private ProtoSerilizerAndDeserilizer m_serilizer;
    private UrlPathProvider.UrlPathEnum m_pathEnum;

    public ASearchService(Class<LResp> clazz,UrlPathProvider.UrlPathEnum pathEnum) {
        m_clazz = clazz;
        m_serilizer = new ProtoSerilizerAndDeserilizer();
        m_pathEnum = pathEnum;
    }

    @Override
    protected LResp doInBackground(LReq... Pbs) {
        ServerUrlManeger urlManeger = new ServerUrlManeger();
        HttpCaller caller = new HttpCaller(RequestMethodEnum.GET, RequestContentTypeEnum.CONTENT_TYPE_JSON, GetUrlMaker.getUrlWIthQuery(urlManeger.getServerUrl(m_pathEnum), URlEncoder.encodeJson(m_serilizer.getJsonObject(Pbs[0]).toString())), null);
        try {
            return ProtoJsonUtil.fromJson(caller.execute().toString(), m_clazz);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}