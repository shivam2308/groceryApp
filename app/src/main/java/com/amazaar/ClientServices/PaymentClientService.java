package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.Protobuff.PaymentPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class PaymentClientService extends AClientService<PaymentPbOuterClass.PaymentPb, PaymentPbOuterClass.PaymentSearchRequestPb, PaymentPbOuterClass.PaymentSearchResponsePb> {
    @Inject
    public PaymentClientService() {
        super(PaymentPbOuterClass.PaymentPb.class, PaymentPbOuterClass.PaymentSearchResponsePb.class, UrlPathProvider.UrlPathEnum.PAYMENT);
    }
}
