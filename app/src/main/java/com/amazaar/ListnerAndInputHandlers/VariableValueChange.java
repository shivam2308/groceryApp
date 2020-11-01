package com.amazaar.ListnerAndInputHandlers;

import javax.inject.Inject;

public class VariableValueChange<T> {
    private T var;
    private ChangeListener listener;

    @Inject
    public VariableValueChange(){

    }

    public T getVar() {
        return var;
    }

    public void setVar(T var) {
        this.var = var;
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