package com.amazaar.Interfaces;

import com.google.protobuf.GeneratedMessageV3;

public interface IPbToModelConvertor<P extends GeneratedMessageV3,M> {
    public M getListModel(P respPb);
}
