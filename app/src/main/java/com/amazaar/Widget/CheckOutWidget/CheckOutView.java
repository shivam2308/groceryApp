package com.amazaar.Widget.CheckOutWidget;

import android.app.Fragment;
import com.amazaar.Fragments.CheckOutFragment;

import javax.inject.Inject;


public class CheckOutView {

    @Inject
    private CheckOutFragment m_mainFragment;

    @Inject
    public CheckOutView() {

    }

    public CheckOutFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(CheckOutFragment checkOutFragment) {
        m_mainFragment = checkOutFragment;
    }
}
