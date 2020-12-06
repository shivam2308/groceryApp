package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.OrderListWidget.OrderListWidget;

public class OrderListFragment extends BaseFragment {
    private OrderListWidget m_orderListWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_order_list, container, false);
        initToolbar();
        setHasOptionsMenu(true);
        initComponents(rootView);
        return rootView;
    }

    @Override
    public void initComponents(View rootView) {

        m_orderListWidget = rootView.findViewById(R.id.orderlist_widget);
        m_orderListWidget.getView().setMainFragment(this);

    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.ORDER_LIST);

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
