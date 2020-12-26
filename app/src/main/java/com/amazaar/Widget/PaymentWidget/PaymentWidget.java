package com.amazaar.Widget.PaymentWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amazaar.ControlFlow.PayAndOrderItem;
import com.amazaar.Interfaces.IView;
import com.amazaar.Protobuff.PaymentPbOuterClass;
import com.amazaar.R;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class PaymentWidget extends LinearLayout implements IView<PaymentView>, View.OnClickListener {

    @Inject
    public PaymentView m_view;
    @Inject
    public PayAndOrderItem m_payAndOrderItem;
    private RadioGroup m_paymentMode;
    private RelativeLayout rlProceesToPay;
    private TextView m_totalAmount;
    private int count = 0;

    public PaymentWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.payment_layout, this);
        rlProceesToPay = (RelativeLayout) findViewById(R.id.fragment_paymentNew_rlProceestoPay);
        m_totalAmount = findViewById(R.id.fragment_order_list_tvTotalKg);
        m_paymentMode = (RadioGroup) findViewById(R.id.payment_mode);
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
        m_totalAmount.setText(String.valueOf(getView().getAmount()));
        m_paymentMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.upi:
                        getView().setMode(PaymentPbOuterClass.PaymentModeEnum.GOOGLE_PAY);
                        break;
                    case R.id.cash_on_delivery:
                        getView().setMode(PaymentPbOuterClass.PaymentModeEnum.CASH_ON_DELIVERY);
                        break;
                }
            }
        });

        rlProceesToPay.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    //getView().payUsingUpi(getView().getAmount());
                    m_payAndOrderItem.createBuyItem("txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612");
                }
                // m_payAndOrderItem.createBuyItem();
                return false;
            }
        });
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
