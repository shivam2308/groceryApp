package com.amazaar.Widget.ProductDetailsWidget;

import com.amazaar.ListModels.ProductListModel;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;

import javax.inject.Inject;

public class ProductDetailsView {

    @Inject
    public VariableValueChange<ProductListModel> m_productListModel;

    @Inject
    public ProductDetailsView() {

    }

    public VariableValueChange<ProductListModel> getProductListModel() {
        return m_productListModel;
    }


}
