package com.amazaar.Widget.PaymentWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amazaar.CommonCode.AToast;
import com.amazaar.ControlFlow.PayAndOrderItem;
import com.amazaar.Interfaces.IView;
import com.amazaar.Protobuff.PaymentPbOuterClass;
import com.amazaar.R;
import com.amazaar.Utility.AndroidUtility;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class PaymentWidget extends LinearLayout implements IView<PaymentView>, View.OnClickListener {

    @Inject
    public PaymentView m_view;
    @Inject
    public PayAndOrderItem m_payAndOrderItem;
    private RadioGroup m_paymentMode;
    private RadioButton m_upi;
    private RelativeLayout rlProceesToPay;
    private TextView m_totalAmount;
    private Button mButton;
    private int count = 0;

    public PaymentWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.payment_layout, this);
        //mButton=(Button)findViewById( R.id.upi);
        rlProceesToPay = (RelativeLayout) findViewById(R.id.fragment_paymentNew_rlProceestoPay);
        m_totalAmount = findViewById(R.id.fragment_order_list_tvTotalKg);
        m_paymentMode = (RadioGroup) findViewById(R.id.payment_mode);
        m_upi = (RadioButton) findViewById(R.id.upi);

        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.payment_layout, this);
        rlProceesToPay.setOnClickListener(this);
        m_paymentMode.setOnClickListener(this);
    }


    public void initWidget() {
        if (!AndroidUtility.isPackageExisted(getView().getGpayPackageName())) {
            m_upi.setVisibility(GONE);
        }

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
                    if (m_paymentMode.getCheckedRadioButtonId() == -1) {
                        AToast.noModeSelected();
                    } else {
                        if(getView().getMode()== PaymentPbOuterClass.PaymentModeEnum.GOOGLE_PAY) {
                            getView().payUsingUpi(getView().getAmount());
                        }else if(getView().getMode()== PaymentPbOuterClass.PaymentModeEnum.CASH_ON_DELIVERY){
                             m_payAndOrderItem.createBuyItem("");
                        }else{
                            //nothing
                        }
                        // m_payAndOrderItem.createBuyItem("txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612");


                    }
                }
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
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
    }
}
