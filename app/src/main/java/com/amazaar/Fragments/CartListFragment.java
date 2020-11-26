package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;

public class CartListFragment extends BaseFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_cart_list, container, false);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }


    @Override
    public void initComponents(View rootView) {
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
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}
