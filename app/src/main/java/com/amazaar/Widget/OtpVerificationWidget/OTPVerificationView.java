package com.amazaar.Widget.OtpVerificationWidget;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskExecutors;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

public class OTPVerificationView {
    private FirebaseAuth mAuth;
    @Inject
    public VariableValueChange<Boolean> isVerified;
    @Inject
    public VariableValueChange<String> m_phonenumber;

    @Inject
    public OTPVerificationView() {
        mAuth = FirebaseAuth.getInstance();
    }

    public VariableValueChange<Boolean> getPhoneIsVerified() {
        return isVerified;
    }
    public VariableValueChange<String> getPhoneNumber() {
        return m_phonenumber;
    }

    public void sendVerificationCode(String mobile, PhoneAuthProvider.OnVerificationStateChangedCallbacks mCallbacks) {
        Log.e("otp", mobile);
        m_phonenumber.setVar(mobile);
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                "+91" + mobile,
                60,
                TimeUnit.SECONDS,
                TaskExecutors.MAIN_THREAD,
                mCallbacks);
    }

    public void verifyVerificationCode(String verificationId, String code) {
        Log.e("otp", code);
        PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationId, code);
        signInWithPhoneAuthCredential(credential);
    }

    public void signInWithPhoneAuthCredential(PhoneAuthCredential credential) {
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.e("otp", "sucessfull");
                            getPhoneIsVerified().setVar(true);
                        } else {
                            task.getException().printStackTrace();
                        }
                    }
                });
    }
}
