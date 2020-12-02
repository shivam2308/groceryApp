package com.amazaar.Widget.PaymentWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.LinearLayoutManager;

import com.amazaar.Interfaces.IView;
import com.amazaar.R;
import com.amazaar.Widget.PaymentWidget.PaymentWidget;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class PaymentWidget extends LinearLayout implements IView<PaymentView>, View.OnClickListener{

    @Inject
    public PaymentView m_view;

    private RelativeLayout rlProceesToPay;

    public PaymentWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.payment_layout, this);
        rlProceesToPay = (RelativeLayout) findViewById(R.id.fragment_paymentNew_rlProceestoPay);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.payment_layout, this);
        rlProceesToPay.setOnClickListener(this);

    }


    private void initWidget() {

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public PaymentView getView() {
        return m_view;
    }

    @Override
    public void onClick(View v) {
    }
}
