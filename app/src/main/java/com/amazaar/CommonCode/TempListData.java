package com.amazaar.CommonCode;

import com.amazaar.ListModels.ProductListModel;
import com.amazaar.R;

import java.util.ArrayList;
import java.util.List;

public class TempListData {
    public List<ProductListModel> getHomeProductList() {

        List<ProductListModel> productListModelArrayList = new ArrayList<>();
        ProductListModel productListModel = new ProductListModel();

        productListModel.setProductImage(R.drawable.category_fruits_vegetables);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_frozen_veg);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_beverages);
        productListModelArrayList.add(productListModel);

        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_branded_food);
        productListModelArrayList.add(productListModel);

        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_beauty_personal_care);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_home_care_fasihon);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_non_veg);
        productListModelArrayList.add(productListModel);

        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_dairy_bakery_eggs);
        productListModelArrayList.add(productListModel);

        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_fruits_vegetables);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_beverages);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_branded_food);
        productListModelArrayList.add(productListModel);

        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_frozen_veg);
        productListModelArrayList.add(productListModel);


        return productListModelArrayList;

    }
}
