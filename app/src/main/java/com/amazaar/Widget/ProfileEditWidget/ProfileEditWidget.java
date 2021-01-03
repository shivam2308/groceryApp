package com.amazaar.Widget.ProfileEditWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.amazaar.Interfaces.IView;
import com.amazaar.R;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getFragmentManager;

public class ProfileEditWidget extends LinearLayout implements IView<ProfileEditView>, View.OnClickListener {
    @Inject
    public ProfileEditView m_view;

    private LinearLayout llContainer;
    private EditText etLoginEmail;
    private EditText etLoginPassword;
    private EditText etSignUpEmail;
    private EditText etSignUpPassword;
    private EditText etSignUpPhone;
    private EditText etSignUpFirstName;
    private EditText etSignUpLastName;

    public ProfileEditWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.profile_edit_layout, this);
        llContainer = (LinearLayout) findViewById(R.id.fragment_login_llContainer);

        etLoginEmail = (EditText) findViewById(R.id.fragment_login_etEmail);
        etLoginPassword = (EditText) findViewById(R.id.fragment_login_etPassword);

//        etSignUpPhone = (EditText) findViewById(R.id.fragment_signup_etPhone);
        etSignUpFirstName = (EditText) findViewById(R.id.fragment_signup_etFirstName);
        etSignUpLastName = (EditText) findViewById(R.id.fragment_signup_etLastName);
        etSignUpEmail = (EditText) findViewById(R.id.fragment_signup_etEmail);
//        etSignUpPassword = (EditText) findViewById(R.id.fragment_signup_etPassword);

        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        // inflate(getContext(), R.layout.help_layout, this);
//        rlSendFeedback.setOnClickListener(this);
//        rlEmailUs.setOnClickListener(this);
//        rlAboutUs.setOnClickListener(this);

    }

    private void initWidget() {

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public ProfileEditView getView() {
        return m_view;
    }
    @Override
    public void onClick(View v) {

    }
}
