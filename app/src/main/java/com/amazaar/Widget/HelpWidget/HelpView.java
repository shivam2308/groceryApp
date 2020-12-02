package com.amazaar.Widget.HelpWidget;

import com.amazaar.Fragments.HelpFragment;

import javax.inject.Inject;

public class HelpView {
    @Inject
    private HelpFragment m_mainFragment;

    @Inject
    public HelpView() {

    }

    public HelpFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(HelpFragment helpFragment) {
        m_mainFragment = helpFragment;
    }
}
