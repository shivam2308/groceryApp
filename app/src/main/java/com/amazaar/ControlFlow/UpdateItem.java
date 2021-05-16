package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.ItemClientService;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class UpdateItem {
    @Inject
    private ItemClientService m_itemClientService;

    @Inject
    public UpdateItem(ItemClientService itemClientService){
        m_itemClientService = itemClientService;
    }
    public IFuture<ItemPbOuterClass.ItemPb, Exception> updateItem(ItemPbOuterClass.ItemPb request){
        UpdateItemCF cf = new UpdateItemCF(request, m_itemClientService);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();
    }
}
