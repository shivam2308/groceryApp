package com.amazaar.Activity;


import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;

import com.amazaar.ControlFlow.CustomerLogin;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Widget.LoadingWIdget.LoadingWidget;
import com.amazaar.Widget.OtpVerificationWidget.OTPVerificationWidget;
import com.amazaar.Widget.ProfileSubmitWidget.ProfileSubmitWidget;
import com.github.ybq.android.spinkit.style.Circle;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getContext;


public class BaseActivity extends AppCompatActivity {

    @Inject
    public CustomerLogin m_customer_login;
    private OTPVerificationWidget m_otpOtpVerificationWidget;
    private ProfileSubmitWidget m_profileSubmitWidget;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        m_otpOtpVerificationWidget = (OTPVerificationWidget) findViewById(R.id.otp_verification);
        m_profileSubmitWidget = (ProfileSubmitWidget) findViewById(R.id.profileSubmit);
        progressBar = (ProgressBar) findViewById(R.id.progress);
        Circle circle = new Circle();
        circle.setBounds(0, 0, 100, 100);
        circle.setColor(R.color.colorPrimary);
        progressBar.setIndeterminateDrawable(circle);
        m_profileSubmitWidget.setVisibility(View.GONE);
        m_otpOtpVerificationWidget.setVisibility(View.VISIBLE);
        injectMembers();
        m_otpOtpVerificationWidget.getView().getPhoneNumber().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                m_profileSubmitWidget.getView().setPhoneNumber(m_otpOtpVerificationWidget.getView().getPhoneNumber().getVar());
            }
        });
        m_otpOtpVerificationWidget.getView().getPhoneIsVerified().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                m_customer_login.login(m_otpOtpVerificationWidget.getView().getPhoneNumber().getVar(), m_profileSubmitWidget);

            }

        });
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        AmazaarApplication.setCurrentActivity(this);
        // qrReaderFragment.getQRCodeReaderWidget().getView().getQrCodeReaderView().startCamera();
    }
}