package com.amazaar.Widget.ProductDetailsWidget;

import com.amazaar.Fragments.ProductDetailsFragment;
import com.amazaar.Fragments.UploadImageFragment;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;

import javax.inject.Inject;

public class ProductDetailsView {

    @Inject
    public VariableValueChange<ProductListModel> m_productListModel;
    UploadImageFragment m_uploadImageFragment;
    ProductDetailsFragment m_productDetailsFragment;

    @Inject
    public ProductDetailsView() {

    }

    public VariableValueChange<ProductListModel> getProductListModel() {
        return m_productListModel;
    }

    public UploadImageFragment getUploadFragment(){
        return m_uploadImageFragment;
    }
    public ProductDetailsFragment getMainFragment(){
        return m_productDetailsFragment;
    }

    public void setUploadFragment(UploadImageFragment uploadImageFragment) {
        m_uploadImageFragment = uploadImageFragment;
    }

    public void setMainFragment(ProductDetailsFragment productDetailsFragment) {
        m_productDetailsFragment=productDetailsFragment;
    }
}
