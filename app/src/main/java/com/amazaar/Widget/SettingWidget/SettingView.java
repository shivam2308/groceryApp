package com.amazaar.Widget.SettingWidget;

import com.amazaar.Fragments.SettingFragment;

import javax.inject.Inject;

public class SettingView {
    @Inject
    private SettingFragment m_mainFragment;

    @Inject
    public SettingView() {

    }

    public SettingFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(SettingFragment settingFragment) {
        m_mainFragment = settingFragment;
    }
}
