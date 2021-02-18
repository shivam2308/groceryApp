package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.CommonCode.VoidPb;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class CloseAndOutForDeliveryClientService extends AClientService<BuyPbOuterClass.BuySearchResponsePb, VoidPb, VoidPb> {

    @Inject
    public CloseAndOutForDeliveryClientService() {
        super(BuyPbOuterClass.BuySearchResponsePb.class, null, UrlPathProvider.UrlPathEnum.CLOSE_AND_OUT_FOR_DELIVERY);
    }
}
