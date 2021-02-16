package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.CreateImageClientService;
import com.amazaar.ClientServices.CustomerClientService;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class CreateImage {

    private CreateImageClientService m_createImageClientService;
    private CustomerSession m_customerSession;
    private CustomerClientService m_customerService;

    @Inject
    public CreateImage(CreateImageClientService createImageClientService, CustomerSession customerSession, CustomerClientService customerService) {
        m_createImageClientService = createImageClientService;
        m_customerSession = customerSession;
        m_customerService = customerService;
    }

    public IFuture<Void, Exception> createImage(ImagePbOuterClass.ImagePb imagePb) {
        CreateImageCF cf = new CreateImageCF(imagePb, m_createImageClientService, m_customerSession, m_customerService);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();
    }
}
