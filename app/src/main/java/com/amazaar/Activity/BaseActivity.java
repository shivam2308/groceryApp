package com.amazaar.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.R;
import com.amazaar.Widget.LoadingWIdget.LoadingWidget;
import com.amazaar.Widget.OtpVerificationWidget.OTPVerificationWidget;
import com.amazaar.Widget.ProfileSubmitWidget.ProfileSubmitWidget;


public class BaseActivity extends AppCompatActivity {

    private OTPVerificationWidget m_otpOtpVerificationWidget;
    private ProfileSubmitWidget m_profileSubmitWidget;
    private LoadingWidget m_loadingWidget;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        m_otpOtpVerificationWidget = (OTPVerificationWidget) findViewById(R.id.otp_verification);
        m_profileSubmitWidget = (ProfileSubmitWidget) findViewById(R.id.profileSubmit);
        m_loadingWidget = (LoadingWidget) findViewById(R.id.loading);
        m_profileSubmitWidget.setVisibility(View.GONE);
        m_otpOtpVerificationWidget.setVisibility(View.GONE);
        m_loadingWidget.setVisibility(View.VISIBLE);
        m_otpOtpVerificationWidget.getView().getPhoneNumber().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                m_profileSubmitWidget.getView().setPhoneNumber(m_otpOtpVerificationWidget.getView().getPhoneNumber().getVar());
            }
        });
        m_otpOtpVerificationWidget.getView().getPhoneIsVerified().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                if (m_otpOtpVerificationWidget.getView().getPhoneIsVerified().getVar()) {
                    m_profileSubmitWidget.setVisibility(View.GONE);
                    m_profileSubmitWidget.setVisibility(View.VISIBLE);
                }
            }
        });
    }
}