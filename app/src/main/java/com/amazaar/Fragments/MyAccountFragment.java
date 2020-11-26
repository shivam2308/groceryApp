package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.MyAccountWidget.MyAccountWidget;

public class MyAccountFragment extends BaseFragment{

    private MyAccountWidget m_accountWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_profile, container, false);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.PROFILE);
    }

    @Override
    public void initComponents(View rootView) {
        m_accountWidget = (MyAccountWidget) rootView.findViewById(R.id.myAccount);
        m_accountWidget.getView().setMainFragment(this);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initToolbar();
        }
    }
}
