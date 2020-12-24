package com.amazaar.Interfaces;

import com.google.protobuf.Message;

public interface IDefaultBuilderPbProvider<Pb> {
    public Pb getDefaultPb();
    public Message.Builder getDefaultBuilderPb();
}
