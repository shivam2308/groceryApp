package com.amazaar.DefaultProviders;


import com.amazaar.Interfaces.IDefaultBuilderPbProvider;
import com.amazaar.Protobuff.OrderedListPbOuterClass;
import com.google.protobuf.Message;

public class OrderedListDefaultPbProvider implements IDefaultBuilderPbProvider<OrderedListPbOuterClass.OrderedListPb> {

    @Override
    public OrderedListPbOuterClass.OrderedListPb getDefaultPb() {
        return OrderedListPbOuterClass.OrderedListPb.getDefaultInstance();
    }

    @Override
    public Message.Builder getDefaultBuilderPb() {
        return OrderedListPbOuterClass.OrderedListPb.newBuilder();
    }
}
