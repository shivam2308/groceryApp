package com.amazaar.CommonCode;

import com.amazaar.Interfaces.IDefaultBuilderPbProvider;
import com.amazaar.Interfaces.IDefaultPbProvider;

import javax.inject.Inject;

public class DataModel<P, DP extends IDefaultBuilderPbProvider<P>> {
    private P m_pb;
    private ChangeListener listener;

    @Inject
    public DataModel(DP dPb) {
        m_pb = dPb.getDefaultPb();
    }

    public P getData() {
        if (listener != null) listener.onChange();
        return m_pb;
    }

    public void setVar(P pb) {
        this.m_pb = pb;
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
