package com.amazaar.DefaultProviders;

import com.amazaar.Interfaces.IDefaultBuilderPbProvider;
import com.amazaar.Protobuff.CartPbOuterClass;
import com.google.protobuf.Message;

import javax.inject.Inject;

public class CartPbDefaultProvider implements IDefaultBuilderPbProvider<CartPbOuterClass.CartPb> {
    @Inject
    public CartPbDefaultProvider() {
    }

    @Override
    public CartPbOuterClass.CartPb getDefaultPb() {
        return CartPbOuterClass.CartPb.getDefaultInstance();
    }

    @Override
    public Message.Builder getDefaultBuilderPb() {
        return CartPbOuterClass.CartPb.newBuilder();
    }
}
