package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.ImageClientService;
import com.amazaar.CommonCode.ImageCacheLoader.ImageLoader;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getContext;

public class GetImageAndStoreInCache {

    @Inject
    public ImageClientService m_imageService;
    public ImageLoader m_imageLoader;

    @Inject
    public GetImageAndStoreInCache() {
        m_imageLoader = new ImageLoader(getContext());
        injectMembers();
    }

    public void  storeImageInCache() {
        GetImageAndStoreInCacheCF cf = new GetImageAndStoreInCacheCF(m_imageService,m_imageLoader);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        cf.getFutureResult();
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }
}
