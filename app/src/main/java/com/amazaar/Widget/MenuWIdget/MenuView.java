package com.amazaar.Widget.MenuWIdget;

import com.amazaar.Fragments.MenuFragment;
import com.amazaar.Fragments.MyAccountFragment;

import javax.inject.Inject;

public class MenuView {

    private MenuFragment m_mainFragment;
    private MyAccountFragment m_myAccountFragment;

    @Inject
    public MenuView() {

    }

    public MenuFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(MenuFragment fragment) {
        m_mainFragment = fragment;
    }

    public MyAccountFragment getMyAccountFragment() {
        return m_myAccountFragment;
    }

    public void setMyAccountFragment(MyAccountFragment myAccountFragment) {
        m_myAccountFragment = myAccountFragment;
    }
}



