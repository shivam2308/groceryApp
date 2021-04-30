package com.amazaar.Widget.PaymentWidget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.amazaar.Fragments.PaymentFragment;
import com.amazaar.Protobuff.PaymentPbOuterClass;

import javax.inject.Inject;

public class PaymentView {
    @Inject
    private PaymentFragment m_mainFragment;
    private PaymentPbOuterClass.PaymentModeEnum m_mode;
    private float m_amount;
    private Activity m_activity;
    private final String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";

    @Inject
    public PaymentView() {

    }

    public PaymentPbOuterClass.PaymentModeEnum getMode() {
        return m_mode;
    }

    public void setMode(PaymentPbOuterClass.PaymentModeEnum mode) {
        m_mode = mode;
    }

    public float getAmount() {
        return m_amount;
    }
    public String getGpayPackageName() {
        return GOOGLE_PAY_PACKAGE_NAME;
    }

    public void setAmount(float amount) {
        m_amount = amount;
    }

    public PaymentFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(PaymentFragment paymentFragment) {
        m_mainFragment = paymentFragment;
    }

    public void setActivity(Activity activity) {
        m_activity = activity;
    }

    public void payUsingUpi(float amount) {
        Log.e("main ", "Paying");
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", "9305500417@okbizaxis")
                .appendQueryParameter("pn", "Amazaar")
                .appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                .appendQueryParameter("tr", "25584584")
                .appendQueryParameter("tn", "Amazaar Apka Apna Bazaar")
                .appendQueryParameter("am", String.valueOf(amount))
                //.appendQueryParameter("am", "1")
                .appendQueryParameter("cu", "INR")
                .build();


        int GOOGLE_PAY_REQUEST_CODE = 123;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        m_activity.startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
    }

}
