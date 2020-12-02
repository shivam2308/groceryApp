package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.PaymentWidget.PaymentWidget;

public class PaymentFragment extends BaseFragment {
    private PaymentWidget m_paymentWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_payment, container, false);
        initToolbar();
        initComponents(rootView);
        return rootView;
    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.PAYMENT);

    }

    @Override
    public void initComponents(View rootView) {
        m_paymentWidget = rootView.findViewById(R.id.payment_widget);
        m_paymentWidget.getView().setMainFragment(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(!hidden)
        {
            initToolbar();
        }
    }
}
