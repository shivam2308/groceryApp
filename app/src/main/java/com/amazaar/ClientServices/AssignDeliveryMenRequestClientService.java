package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.CommonCode.VoidPb;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class AssignDeliveryMenRequestClientService extends AClientService<BuyPbOuterClass.BuySearchResponsePb, VoidPb,VoidPb> {
    @Inject
    public AssignDeliveryMenRequestClientService() {
        super(BuyPbOuterClass.BuySearchResponsePb.class,null, UrlPathProvider.UrlPathEnum.ASSIGN_DELIVERY_MAN);
    }
}
