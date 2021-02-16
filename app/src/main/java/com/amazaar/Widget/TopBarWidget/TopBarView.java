package com.amazaar.Widget.TopBarWidget;

import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.Fragments.HomeCategoryFragment;
import com.amazaar.Fragments.MenuFragment;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;

import javax.inject.Inject;

public class TopBarView {

    private HomeCategoryFragment m_mainFragment;
    private MenuFragment m_menuFragment;
    private VariableValueChange<TopBarUiEnum> m_topBarChange;


    @Inject
    public TopBarView(VariableValueChange<TopBarUiEnum> topBarChange) {
        m_topBarChange = topBarChange;
    }

    public VariableValueChange<TopBarUiEnum> getTopBarChange() {
        return m_topBarChange;
    }

    public HomeCategoryFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(HomeCategoryFragment fragment) {
        m_mainFragment = fragment;
    }

    public MenuFragment getMenuFragment() {
        return m_menuFragment;
    }

    public void setMenuFragment(MenuFragment menuFragment) {
        m_menuFragment = menuFragment;
    }


}
