package com.amazaar.Widget.LoadingWIdget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.R;
import com.google.inject.Injector;
import com.vlonjatg.progressactivity.ProgressLayout;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getFragmentManager;

public class LoadingWidget extends LinearLayout implements IView<LoadingView> {

    @Inject
    public LoadingView m_view;
    public ProgressLayout m_progressLayout;


    public LoadingWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.loading_layout, this);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.loading_layout, this);
        m_progressLayout = (ProgressLayout) findViewById(R.id.progress);

    }

    private void initWidget() {
        getView().setProgressLayout(m_progressLayout);
        getView().getOnChange().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                if (getView().getOnChange().getVar() == true) {
                    getView().getDailogLodading().show(getFragmentManager(), "Loading");
                } else {
                    getView().getDailogLodading().dismiss();
                }
            }
        });
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public LoadingView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }
}
