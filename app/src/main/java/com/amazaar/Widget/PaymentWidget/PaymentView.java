package com.amazaar.Widget.PaymentWidget;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.amazaar.Fragments.PaymentFragment;

import javax.inject.Inject;

public class PaymentView {
    @Inject
    private PaymentFragment m_mainFragment;

    private Activity m_activity;

    @Inject
    public PaymentView() {

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

    public void payUsingUpi() {
        Log.e("main ", "Paying");
        Uri uri = Uri.parse("upi://pay").buildUpon()
                .appendQueryParameter("pa", "shubhamtiwaricr07@okaxis")
                .appendQueryParameter("pn", "Shubham Tiwari")
                .appendQueryParameter("mc", "")
                //.appendQueryParameter("tid", "02125412")
                .appendQueryParameter("tr", "25584584")
                .appendQueryParameter("tn", "orderId #1234567890")
                .appendQueryParameter("am", "1")
                .appendQueryParameter("cu", "INR")
                .build();

        String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
        int GOOGLE_PAY_REQUEST_CODE = 123;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(uri);
        intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
        m_activity.startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
    }

}
