package com.amazaar.Widget.MyAccountWidget;

import com.amazaar.Fragments.MyAccountFragment;

import javax.inject.Inject;

public class MyAccountView {

    private MyAccountFragment m_mainFragment;

    @Inject
    MyAccountView() {

    }

    public MyAccountFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(MyAccountFragment fragment) {
        m_mainFragment = fragment;
    }
}
