package com.amazaar.Widget.PaymentWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
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
    private RadioGroup m_paymentMode;



    private RelativeLayout rlProceesToPay;

    public PaymentWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }
    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.payment_layout, this);
        rlProceesToPay = (RelativeLayout) findViewById(R.id.fragment_paymentNew_rlProceestoPay);
        m_paymentMode =(RadioGroup) findViewById(R.id.payment_mode);
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
        m_paymentMode.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch(checkedId){
                    case R.id.upi:

                        break;
                    case R.id.cash_on_delivery:

                        break;
                }
            }
        });

        rlProceesToPay.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                getView().payUsingUpi();
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
