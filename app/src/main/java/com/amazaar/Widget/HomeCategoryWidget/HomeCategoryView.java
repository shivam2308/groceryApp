package com.amazaar.Widget.HomeCategoryWidget;

import com.amazaar.Fragments.HomeCategoryFragment;
import com.amazaar.Fragments.ProductListFragment;

import javax.inject.Inject;

public class HomeCategoryView {

    @Inject
    private HomeCategoryFragment m_mainFragment;
    private ProductListFragment m_fragmentProductDetails;

    @Inject
    public HomeCategoryView() {

    }

    public HomeCategoryFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(HomeCategoryFragment homeCategoryFragment) {
        m_mainFragment = homeCategoryFragment;
    }
    public ProductListFragment getProductListFragment(){
        return m_fragmentProductDetails;
    }

    public void setProductListFragment(ProductListFragment fragmentProductDetails) {
        m_fragmentProductDetails = fragmentProductDetails;
    }
}