package com.amazaar.BaseClientService;

import com.amazaar.Interfaces.IClientServices;
import com.amazaar.ServerConfig.UrlPathProvider;
import com.google.protobuf.GeneratedMessageV3;

import java.util.concurrent.ExecutionException;

public class AClientService<P extends GeneratedMessageV3, Req extends GeneratedMessageV3, Resp extends GeneratedMessageV3> implements IClientServices<P, Req, Resp> {
    private AGetService<P> m_get;
    private ACreateService<P> m_create;
    private AUpdateService<P> m_update;
    private ASearchService<Req, Resp> m_search;
    private Class<P> m_Pclazz;
    private UrlPathProvider.UrlPathEnum m_pathEnum;
    private Class<Resp> m_Respclazz;

    public AClientService(Class<P> Pclazz, Class<Resp> Respclazz, UrlPathProvider.UrlPathEnum pathEnum) {
        m_Pclazz=Pclazz;
        m_pathEnum= pathEnum;
        m_Respclazz = Respclazz;
        m_get = new AGetService<P>(Pclazz, pathEnum);
        m_create = new ACreateService<P>(Pclazz, pathEnum);
        m_update = new AUpdateService<P>(Pclazz, pathEnum);
        m_search = new ASearchService<Req, Resp>(Respclazz, pathEnum);
    }

    @Override
    public P get(String id) throws ExecutionException, InterruptedException {
        //return m_get.execute(id).get();
        return new AGetService<P>(m_Pclazz, m_pathEnum).execute(id).get();
    }

    @Override
    public P create(P request) throws ExecutionException, InterruptedException {
        return m_create.execute(request).get();
    }

    @Override
    public P update(P request) throws ExecutionException, InterruptedException {
        return m_update.execute(request).get();
    }

    @Override
    public Resp search(Req request) throws ExecutionException, InterruptedException {
        return  new ASearchService<Req, Resp>(m_Respclazz, m_pathEnum).execute(request).get();
    }
}
