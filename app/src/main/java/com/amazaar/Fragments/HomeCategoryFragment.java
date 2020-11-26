package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.HomeCategoryWidget.HomeCategoryWidget;

public class HomeCategoryFragment extends BaseFragment {

    private HomeCategoryWidget m_homeCategoryWidget;
    private MenuItem item;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_home, container, false);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }


    @Override
    public void initComponents(View rootView) {
        m_homeCategoryWidget = rootView.findViewById(R.id.home_category);
        m_homeCategoryWidget.getView().setMainFragment(this);
    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.HOME);

    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        item = menu.findItem(R.id.menu_left);
        item.setVisible(false);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initToolbar();
        }
    }
}
