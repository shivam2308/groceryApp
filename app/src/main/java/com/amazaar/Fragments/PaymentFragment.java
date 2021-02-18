package com.amazaar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.CommonCode.AToast;
import com.amazaar.CommonCode.CommonHelper;
import com.amazaar.ControlFlow.PayAndOrderItem;
//import com.amazaar.Dialog.PaymentProgressDailogFragment;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Widget.PaymentWidget.PaymentWidget;
import com.google.inject.Injector;
import com.kofigyan.stateprogressbar.StateProgressBar;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static android.app.Activity.RESULT_OK;

public class PaymentFragment extends BaseFragment {

    public final int UPI_PAYMENT = 123;
    @Inject
    public CommonHelper m_helper;
    @Inject
    public PayAndOrderItem m_payAndOrderItem;
    private PaymentWidget m_paymentWidget;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_payment, container, false);
        injectMembers();
        initToolbar();
        initComponents(rootView);
        m_paymentWidget.getView().setAmount(Float.parseFloat(getArguments().getString("totalAmount")));
        return rootView;
    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.PAYMENT);

    }

    @Override
    public void initComponents(View rootView) {
        m_paymentWidget = rootView.findViewById(R.id.payment_widget);
        m_paymentWidget.getView().setMainFragment(this);
        m_paymentWidget.getView().setActivity(getActivity());
        m_helper = new CommonHelper();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            initToolbar();
        }
    }

    //must call in Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e("main ", "response " + resultCode);
        /*
       E/main: response -1
       E/UPI: onActivityResult: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPIPAY: upiPaymentDataOperation: txnId=AXI4a3428ee58654a938811812c72c0df45&responseCode=00&Status=SUCCESS&txnRef=922118921612
       E/UPI: payment successfull: 922118921612
         */
        switch (requestCode) {
            case UPI_PAYMENT:
                if ((RESULT_OK == resultCode) || (resultCode == 11)) {
                    if (data != null) {
                        String trxt = data.getStringExtra("response");
                        Log.e("UPI", "onActivityResult: " + trxt);
                        PayAndOrderItem cf = new PayAndOrderItem();
                        cf.createBuyItem(trxt);
                    } else {
                        AToast.userPaymentFailed();
                    }
                } else {
                    AToast.userPaymentFailed();
                }
                break;
        }
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }


}