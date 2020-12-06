package com.amazaar.Widget.OrderListWidget;

import com.amazaar.Fragments.OrderListFragment;

import javax.inject.Inject;

public class OrderListView {
    private OrderListFragment m_mainFragment;

    @Inject
    public OrderListView(){

    }

    public OrderListFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(OrderListFragment fragment) {
        m_mainFragment = fragment;
    }
}
