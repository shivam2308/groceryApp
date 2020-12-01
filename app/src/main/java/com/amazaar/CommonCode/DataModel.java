package com.amazaar.CommonCode;

import com.amazaar.Interfaces.IDefaultBuilderPbProvider;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.Message;

import javax.inject.Inject;

public class DataModel<P extends GeneratedMessageV3, DP extends IDefaultBuilderPbProvider<P>> {
    private Message.Builder m_pb;
    private ChangeListener listener;
    private P m_dPb;

    @Inject
    public DataModel(DP dPb) {
        m_pb = dPb.getDefaultBuilderPb();
        m_dPb= dPb.getDefaultPb();
    }

    public P getData() {
        return (P) m_pb.build();
    }

    public P getDefaultData() {
        return m_dPb;
    }

    public Message.Builder getBuilderData() {
        return m_pb;
    }

    public void setVar(P pb) {
        this.m_pb = (Message.Builder) pb.toBuilder();
        if (listener != null) listener.onChange();
    }

    public ChangeListener getListener() {
        return listener;
    }

    public void setListener(ChangeListener listener) {
        this.listener = listener;
    }

    public interface ChangeListener {
        void onChange();
    }
}
