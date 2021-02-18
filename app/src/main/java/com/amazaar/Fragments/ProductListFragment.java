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
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.amazaar.Widget.ProductListWidget.ProductListWidget;

import javax.inject.Inject;

public class ProductListFragment extends BaseFragment {

    private ItemPbOuterClass.ItemTypeEnum m_itemtype;
    private MenuItem item;
    @Inject
    private ProductListWidget m_productListWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_product_list, container, false);

        initComponents(rootView);
        setHasOptionsMenu(true);
        initToolbar();
        m_productListWidget.getView().getItemType().setVar(m_itemtype);
        return rootView;

    }


    @Override
    public void initComponents(View rootView) {
        m_productListWidget = (ProductListWidget) rootView.findViewById(R.id.productListWidget);
        m_productListWidget.getView().setMainFragment(this);
    }

    public void setItemType(ItemPbOuterClass.ItemTypeEnum itemType) {
        m_itemtype = itemType;
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        item = menu.findItem(R.id.menu_left);
        item.setVisible(true);
        item.setIcon(R.drawable.cart);
        item.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Utils.hideKeyboard(getActivity());
                CartListFragment fragmentProductDetails = new CartListFragment();
                fragmentProductDetails.setTargetFragment(ProductListFragment.this, 222);
                //Utils.addNextFragment(get(), fragmentProductDetails, ProductListFragment.this, false);

                return true;
            }
        });
    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.PRODUCT_DETAILS);
       /* if (getTargetFragment() != null) {

            m_productListWidget.getView().getiFilter().setVisibility(View.GONE);
            ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.WISH_LIST);

        } else {
            m_productListWidget.getView().getiFilter().setVisibility(View.VISIBLE);
            ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.HOME);
        }*/

       /* ((HomeActivity) getActivity()).getIvCart().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CartListFragment mFragment = new CartListFragment();
                Utils.addNextFragment(getContext(), mFragment, ProductListFragment.this, true);
            }
        });
*/
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initToolbar();
        }
    }
}
