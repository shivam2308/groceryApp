package com.amazaar.Widget.MenuWIdget;

import com.amazaar.Fragments.MenuFragment;

import javax.inject.Inject;

public class MenuView {

    private MenuFragment m_mainFragment;

    @Inject
    public MenuView(){

    }

    public MenuFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(MenuFragment fragment) {
        m_mainFragment = fragment;
    }
}



