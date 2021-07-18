package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.SettingWidget.SettingWidget;

public class SettingFragment extends BaseFragment {
    private SettingWidget m_settingWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_setting, container, false);
        initToolbar();
        initComponents(rootView);
        return rootView;
    }

    @Override
    public void initComponents(View rootView) {

        m_settingWidget = rootView.findViewById(R.id.setting_widget);
        m_settingWidget.getView().setMainFragment(this);

    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.SETTING);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(!hidden)
        {
            initToolbar();
            m_settingWidget.initWidget();
        }
    }
}
