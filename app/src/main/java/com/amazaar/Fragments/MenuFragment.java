package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Widget.MenuWIdget.MenuWidget;

public class MenuFragment extends BaseFragment {

    private MenuWidget m_menuWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_menu, container, false);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }

    public void initToolbar() {
        ((HomeActivity)AmazaarApplication.getCurrentActivity()).setToolbar(TopBarUiEnum.MENU);
    }

    @Override
    public void initComponents(View rootView) {
        m_menuWidget = rootView.findViewById(R.id.menuWIdget);
        m_menuWidget.getView().setMainFragment(this);
    }

    public MenuWidget getMenuWidget() {
        return m_menuWidget;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initToolbar();
            m_menuWidget.initWidget();
        }
    }
}
