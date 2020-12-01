package com.amazaar.DefaultProviders;

import com.amazaar.Interfaces.IDefaultBuilderPbProvider;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.google.protobuf.Message;

public class ItemPbDefultProvider implements IDefaultBuilderPbProvider<ItemPbOuterClass.ItemPb> {
    @Override
    public ItemPbOuterClass.ItemPb getDefaultPb() {
        return ItemPbOuterClass.ItemPb.getDefaultInstance();
    }

    @Override
    public Message.Builder getDefaultBuilderPb() {
        return ItemPbOuterClass.ItemPb.newBuilder();
    }
}
