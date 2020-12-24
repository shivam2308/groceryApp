package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.ProductDetailsWidget.ProductDetailsWidget;

public class ProductDetailsFragment extends BaseFragment {

    public ProductDetailsWidget m_productDetailsWidget;

    private Bundle bundle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View rootView = inflater.inflate(R.layout.fragment_product_detail, container, false);

        setHasOptionsMenu(true);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bundle = getArguments();
    }

    @Override
    public void initComponents(View rootView) {
        m_productDetailsWidget = (ProductDetailsWidget) rootView.findViewById(R.id.product_details_widget);
        if (bundle != null) {
           // m_productDetailsWidget.getView().setProductListModel(bundle.getParcelable(getString(R.string.bdl_model)));
        }
    }

    public void initToolbar() {

        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.PRODUCT_DETAILS);

        /*((HomeActivity) getActivity()).getIvCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartListFragment mFragment = new CartListFragment();
                Utils.addNextFragment(getActivity(), mFragment, ProductDetailsFragment.this, true);
            }
        });*/

    }
}
