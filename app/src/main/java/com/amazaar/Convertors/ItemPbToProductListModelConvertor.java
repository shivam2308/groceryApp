package com.amazaar.Convertors;

import com.amazaar.Interfaces.IPbToModelConvertor;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.prod.basic.common.collect.Lists;

import java.util.List;

import javax.inject.Inject;

public class ItemPbToProductListModelConvertor implements IPbToModelConvertor<ItemPbOuterClass.ItemSearchResponsePb, List<ProductListModel>> {

    @Inject
    public ItemPbToProductListModelConvertor()
    {

    }
    @Override
    public List<ProductListModel> getListModel(ItemPbOuterClass.ItemSearchResponsePb respPb) {
        List<ProductListModel> listModel = Lists.newArrayList();
        for (ItemPbOuterClass.ItemPb itemPb:respPb.getResultsList()) {
            ProductListModel productListModel = new ProductListModel();
            productListModel.setPbModel(itemPb);
            listModel.add(productListModel);
        };
        return listModel;
    }
}
