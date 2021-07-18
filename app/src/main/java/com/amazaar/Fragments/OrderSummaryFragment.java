package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.ListModels.OrderListModel;
import com.amazaar.R;
import com.amazaar.Widget.OrderSummaryWidget.OrderSummaryWidget;


import javax.inject.Inject;

public class OrderSummaryFragment extends BaseFragment{

    @Inject
    private OrderSummaryWidget m_orderSummeryWidget;
    private OrderListModel model;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.order_summary_fragment, container, false);

        initComponents(rootView);
        //setHasOptionsMenu(true);
        return rootView;

    }

    @Override
    public void initComponents(View rootView) {
        m_orderSummeryWidget = (OrderSummaryWidget) rootView.findViewById(R.id.ordersummarywidget);
        m_orderSummeryWidget.getView().setMainFragment(this);
        m_orderSummeryWidget.getView().getOrderParentId().setVar(model);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            m_orderSummeryWidget.initWidget();
        }
    }

    public void setModel(OrderListModel viewModel) {
        model=viewModel;
    }
}