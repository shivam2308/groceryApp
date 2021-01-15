package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.CommonCode.VoidPb;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

//use only create Methoed.....
public class CreateBuyClientService extends AClientService<BuyPbOuterClass.CreateBuyRequestPb, VoidPb,VoidPb> {
    @Inject
    public CreateBuyClientService() {
        super(BuyPbOuterClass.CreateBuyRequestPb.class, null, UrlPathProvider.UrlPathEnum.CREATE_BUY);
    }
}
