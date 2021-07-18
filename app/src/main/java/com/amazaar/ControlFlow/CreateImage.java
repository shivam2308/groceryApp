package com.amazaar.ControlFlow;

import android.widget.ImageView;

import com.amazaar.ClientServices.CreateImageClientService;
import com.amazaar.ClientServices.CustomerClientService;
import com.amazaar.CommonCode.ImageCacheLoader.ImageLoader;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

import static com.amazaar.Module.AmazaarApplication.getContext;

public class CreateImage {

    private CreateImageClientService m_createImageClientService;
    private CustomerSession m_customerSession;
    private CustomerClientService m_customerService;
    private ImageLoader m_imageLoader;

    @Inject
    public CreateImage(CreateImageClientService createImageClientService, CustomerSession customerSession, CustomerClientService customerService) {
        m_createImageClientService = createImageClientService;
        m_customerSession = customerSession;
        m_customerService = customerService;
        m_imageLoader= new ImageLoader(getContext());
    }

    public IFuture<Void, Exception> createImage(ImagePbOuterClass.ImagePb imagePb, ImageView imageToBeSet) {
        CreateImageCF cf = new CreateImageCF(imagePb, m_createImageClientService, m_customerSession, m_customerService,m_imageLoader,imageToBeSet);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();
    }
}
