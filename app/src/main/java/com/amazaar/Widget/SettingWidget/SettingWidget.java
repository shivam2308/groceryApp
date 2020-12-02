package com.amazaar.Widget.SettingWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.amazaar.Interfaces.IView;
import com.amazaar.R;
import com.amazaar.Widget.SettingWidget.SettingView;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class SettingWidget extends LinearLayout implements IView<SettingView>, View.OnClickListener {

    @Inject
    public SettingView m_view;

    private RelativeLayout rlPrivacyPolicy;
    private RelativeLayout rlTerms;

    public SettingWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.setting_layout, this);
        rlPrivacyPolicy = (RelativeLayout) findViewById(R.id.fragment_setting_rlPrivacy);
        rlTerms = (RelativeLayout) findViewById(R.id.fragment_setting_rlTermOfUse);

        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.setting_layout, this);
        rlPrivacyPolicy.setOnClickListener(this);
        rlTerms.setOnClickListener(this);

    }

    private void initWidget() {

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public SettingView getView() {
        return m_view;
    }

    public void onClick(View v) {

    }
}
