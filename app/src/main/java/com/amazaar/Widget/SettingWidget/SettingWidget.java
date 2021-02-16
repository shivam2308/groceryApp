package com.amazaar.Widget.SettingWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.amazaar.Interfaces.IView;
import com.amazaar.R;
import com.amazaar.Dialog.DialogFragmentPrivacy;
import com.google.inject.Injector;
import com.amazaar.Dialog.DialogFragmentTerms;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getFragmentManager;

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

    @Override
    public void onBackPressed() {

    }

    public void onClick(View v) {
        //super.onClick(v);

        if (v == rlTerms) {
            DialogFragmentTerms dialogFragmentTerms = new DialogFragmentTerms();
            dialogFragmentTerms.show(getFragmentManager(), getContext().getString(R.string.lbl_TermsOfUse));
        }
        else if (v == rlPrivacyPolicy) {
            DialogFragmentPrivacy dialogFragmentPrivacy = new DialogFragmentPrivacy();
            dialogFragmentPrivacy.show(getFragmentManager(), getContext().getString(R.string.lbl_PrivacyPolicy));
        }
    }

}
