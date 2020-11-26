package com.amazaar.Widget.OtpVerificationWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.R;
import com.amazaar.Utility.AndroidUtility;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;


public class OTPVerificationWidget extends LinearLayout implements IView<OTPVerificationView> {
    private TextView m_sendCodeButton;
    private TextView m_verifyButton;
    private EditText m_phoneNumber;
    private EditText m_otpcode;
    private LinearLayout m_sendOtpLayout, m_verifyOtpLayout;
    private String mVerificationId;
    private boolean isVerified = false;
    private PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks;


    @Inject
    public OTPVerificationView m_view;


    public OTPVerificationWidget(Context context, AttributeSet attrs) {
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
        inflate(getContext(), R.layout.otp_verification_layout, this);
        m_sendOtpLayout = (LinearLayout) findViewById(R.id.mobileNumber);
        m_verifyOtpLayout = (LinearLayout) findViewById(R.id.otp_verify);
        m_otpcode = (EditText) findViewById(R.id.otpCode);
        m_phoneNumber = (EditText) findViewById(R.id.phone_number);
        m_verifyButton = (TextView) findViewById(R.id.verify);
        m_sendCodeButton = (TextView) findViewById(R.id.getCode);
        m_verifyOtpLayout.setVisibility(GONE);
        m_sendOtpLayout.setVisibility(VISIBLE);
    }

    private void initWidget() {
        mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {
            @Override
            public void onVerificationCompleted(@NonNull PhoneAuthCredential phoneAuthCredential) {
                String code = phoneAuthCredential.getSmsCode();
                if (code != null) {
                    m_otpcode.setText(code);
                    getView().verifyVerificationCode(mVerificationId, code);
                }

            }

            @Override
            public void onVerificationFailed(@NonNull FirebaseException e) {
                e.printStackTrace();
            }

            @Override
            public void onCodeSent(@NonNull String s, @NonNull PhoneAuthProvider.ForceResendingToken forceResendingToken) {
                super.onCodeSent(s, forceResendingToken);
                mVerificationId = s;
            }
        };

        m_verifyButton.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getView().verifyVerificationCode(mVerificationId, AndroidUtility.getTextFromEditText(m_otpcode));
                return true;
            }
        });


        m_sendCodeButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getView().sendVerificationCode(AndroidUtility.getTextFromEditText(m_phoneNumber), mCallbacks);
                switchLayout();
                return false;
            }
        });
    }

    private void switchLayout() {
        m_sendOtpLayout.setVisibility(GONE);
        m_verifyOtpLayout.setVisibility(VISIBLE);
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public OTPVerificationView getView() {
        return m_view;
    }
}
