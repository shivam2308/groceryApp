package com.amazaar.Widget.LoadingWIdget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.amazaar.Interfaces.IView;
import com.amazaar.R;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class LoadingWidget extends LinearLayout implements IView<LoadingView> {

    @Inject
    public LoadingView m_view;


    public LoadingWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.otp_verification_layout, this);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.loading_layout, this);

    }

    private void initWidget() {
        inflate(getContext(), R.layout.loading_layout, this);
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public LoadingView getView() {
        return m_view;
    }
}
