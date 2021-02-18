package com.amazaar.Widget.LoadingWIdget;

import com.amazaar.Dialog.DailogLoading;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.vlonjatg.progressactivity.ProgressLayout;

import javax.inject.Inject;

public class LoadingView {

    private DailogLoading m_dailogLodading;
    private VariableValueChange<Boolean> m_onChange;
    private ProgressLayout m_progressLayout;

    @Inject
    public LoadingView() {
        m_dailogLodading = new DailogLoading();
        m_onChange = new VariableValueChange<>();
    }

    public VariableValueChange<Boolean> getOnChange() {
        return m_onChange;
    }

    public DailogLoading getDailogLodading() {
        return m_dailogLodading;
    }

    public ProgressLayout getProgressLayout() {
        return m_progressLayout;
    }

    public void setProgressLayout(ProgressLayout progressLayout) {
        m_progressLayout = progressLayout;
    }
}
