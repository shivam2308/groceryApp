package com.amazaar.Widget.HomeCategoryWidget;

import android.app.Fragment;

import com.amazaar.Fragments.HomeCategoryFragment;

import javax.inject.Inject;

public class HomeCategoryView {

    @Inject
    private HomeCategoryFragment m_mainFragment;

    @Inject
    public HomeCategoryView() {

    }

    public HomeCategoryFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(HomeCategoryFragment homeCategoryFragment) {
        m_mainFragment = homeCategoryFragment;
    }
}
