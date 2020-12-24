package com.amazaar.DefaultProviders;

import com.amazaar.Interfaces.IDefaultBuilderPbProvider;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.google.protobuf.Message;

import javax.inject.Inject;

public class CustomerPbDefaultProvider implements IDefaultBuilderPbProvider<CustomerPbOuterClass.CustomerPb> {
    @Inject
    public CustomerPbDefaultProvider() {
    }

    @Override
    public CustomerPbOuterClass.CustomerPb getDefaultPb() {
        return CustomerPbOuterClass.CustomerPb.getDefaultInstance();
    }

    @Override
    public Message.Builder getDefaultBuilderPb() {
        return CustomerPbOuterClass.CustomerPb.newBuilder();
    }
}
