package com.amazaar.Widget.CartWidget;

import com.amazaar.Fragments.CartListFragment;
import com.amazaar.Fragments.MenuFragment;

import javax.inject.Inject;

public class CartView {
    private CartListFragment m_mainFragment;

    @Inject
    public CartView(){

    }

    public CartListFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(CartListFragment fragment) {
        m_mainFragment = fragment;
    }
}
