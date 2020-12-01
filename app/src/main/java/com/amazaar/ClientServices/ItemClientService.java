package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class ItemClientService extends AClientService<ItemPbOuterClass.ItemPb, ItemPbOuterClass.ItemSearchRequestPb, ItemPbOuterClass.ItemSearchResponsePb> {

    @Inject
    public ItemClientService() {
        super(ItemPbOuterClass.ItemPb.class, ItemPbOuterClass.ItemSearchResponsePb.class, UrlPathProvider.UrlPathEnum.ITEM);
    }
}
