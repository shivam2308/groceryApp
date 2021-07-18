package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.CheckOutWidget.CheckOutWidget;

public class CheckOutFragment extends BaseFragment {
    private CheckOutWidget m_checkOutWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_check_out, container, false);
        initToolbar();
        initComponents(rootView);
//        setAddress();
//        setOrderList();
        return rootView;
    }

    @Override
    public void initComponents(View rootView) {

        m_checkOutWidget = rootView.findViewById(R.id.checkout_widget);
        m_checkOutWidget.getView().setMainFragment(this);

    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.CHECK_OUT);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            if (((HomeActivity) getActivity()) != null) {
                initToolbar();
                m_checkOutWidget.initWidget();
            }
        }
    }
}
