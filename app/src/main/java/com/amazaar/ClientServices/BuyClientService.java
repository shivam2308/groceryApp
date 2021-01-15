package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class BuyClientService extends AClientService<BuyPbOuterClass.BuyPb, BuyPbOuterClass.BuySearchRequestPb, BuyPbOuterClass.BuySearchResponsePb> {
    @Inject
    public BuyClientService() {
        super(BuyPbOuterClass.BuyPb.class, BuyPbOuterClass.BuySearchResponsePb.class, UrlPathProvider.UrlPathEnum.BUY);
    }
}
