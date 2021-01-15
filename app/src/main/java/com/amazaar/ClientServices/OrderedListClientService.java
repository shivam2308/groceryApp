package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.CommonCode.VoidPb;
import com.amazaar.Protobuff.OrderedListPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class OrderedListClientService extends AClientService<VoidPb, OrderedListPbOuterClass.OrderedListSearchReqPb, OrderedListPbOuterClass.OrderedListSearchRespPb> {

    @Inject
    public OrderedListClientService() {
        super(null, OrderedListPbOuterClass.OrderedListSearchRespPb.class, UrlPathProvider.UrlPathEnum.ORDERED_LIST);
    }
}
