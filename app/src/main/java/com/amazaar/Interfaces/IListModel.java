package com.amazaar.Interfaces;

import com.google.protobuf.GeneratedMessageV3;

public interface IListModel<P extends GeneratedMessageV3> {
    public P getPbModel();
    public void setPbModel(P pb);
}
