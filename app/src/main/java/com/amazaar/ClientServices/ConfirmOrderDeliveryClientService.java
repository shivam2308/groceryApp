package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.CommonCode.VoidPb;
import com.amazaar.Protobuff.ConfirmOrderDeliveryPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;
//use only get method......
public class ConfirmOrderDeliveryClientService extends AClientService<ConfirmOrderDeliveryPbOuterClass.ConfirmOrderDeliveryPb, VoidPb, VoidPb> {
    @Inject
    public ConfirmOrderDeliveryClientService() {
        super(ConfirmOrderDeliveryPbOuterClass.ConfirmOrderDeliveryPb.class, null, UrlPathProvider.UrlPathEnum.CONFIRM_ORDER);
    }
}
