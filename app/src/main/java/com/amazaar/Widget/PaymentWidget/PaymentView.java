package com.amazaar.Widget.PaymentWidget;

import com.amazaar.Fragments.PaymentFragment;

import javax.inject.Inject;

public class PaymentView {
    @Inject
    private PaymentFragment m_mainFragment;

    @Inject
    public PaymentView() {

    }

    public PaymentFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(PaymentFragment paymentFragment) {
        m_mainFragment = paymentFragment;
    }
}
