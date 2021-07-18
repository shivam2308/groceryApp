package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.CartWidget.CartWidget;
import com.amazaar.Widget.ProfileEditWidget.ProfileEditWidget;

public class ProfileEditFragment extends BaseFragment{
    private ProfileEditWidget m_profileEditWidget;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_profile_edit, container, false);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }


    @Override
    public void initComponents(View rootView) {
        m_profileEditWidget = rootView.findViewById(R.id.profile_edit);
        m_profileEditWidget.getView().setMainFragment(this);
        initToolbar();
    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.CART);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            try {
                initToolbar();
                m_profileEditWidget.initWidget();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
