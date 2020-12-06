package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.HelpWidget.HelpWidget;

public class HelpFragment extends BaseFragment {
    private HelpWidget m_helpWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_help, container, false);
        initToolbar();
        initComponents(rootView);
        return rootView;
    }

    @Override
    public void initComponents(View rootView) {

        m_helpWidget = rootView.findViewById(R.id.help_widget);
        m_helpWidget.getView().setMainFragment(this);

    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.HELP);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if(!hidden)
        {
            initToolbar();
        }
    }
}
