package com.amazaar.Fragments;

import android.view.View;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;

public class HomeCategoryFragment extends BaseFragment {
    @Override
    public void initComponents(View rootView) {

    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.HOME);

     /*   ((HomeActivity) getActivity()).getIvCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               // CartListFragment mFragment = new CartListFragment();
               // Utils.addNextFragment(getActivity(), mFragment, HomeCategoryFragment.this, true);
            }
        });*/
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initToolbar();
        }
    }
}
