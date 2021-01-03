package com.amazaar.DefaultProviders;

import com.amazaar.Interfaces.IDefaultBuilderPbProvider;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.google.protobuf.Message;

public class BuyPbDefaultProvider implements IDefaultBuilderPbProvider<BuyPbOuterClass.BuyPb> {
    @Override
    public BuyPbOuterClass.BuyPb getDefaultPb() {
        return BuyPbOuterClass.BuyPb.getDefaultInstance();
    }

    @Override
    public Message.Builder getDefaultBuilderPb() {
        return BuyPbOuterClass.BuyPb.newBuilder();
    }
}
