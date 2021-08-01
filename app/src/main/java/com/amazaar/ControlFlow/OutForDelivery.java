package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.BuyClientService;
import com.amazaar.ClientServices.CloseAndOutForDeliveryClientService;
import com.amazaar.Protobuff.ConfirmOrderDeliveryPbOuterClass;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class OutForDelivery {

    private BuyClientService m_buyClientService;
    private CloseAndOutForDeliveryClientService m_CloseAndOutForDeliveryClientService;

    @Inject
    public OutForDelivery(BuyClientService buyClientService, CloseAndOutForDeliveryClientService closeAndOutForDeliveryClientService) {
        m_buyClientService = buyClientService;
        m_CloseAndOutForDeliveryClientService = closeAndOutForDeliveryClientService;
    }

    public IFuture<Void, Exception> outForDelivery(String parentOrderId) {
        OutForDeliveryCF cf = new OutForDeliveryCF(parentOrderId, m_buyClientService, m_CloseAndOutForDeliveryClientService);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();
    }
}
