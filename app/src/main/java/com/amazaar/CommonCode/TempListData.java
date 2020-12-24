package com.amazaar.CommonCode;

import com.amazaar.ListModels.ProductListModel;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.amazaar.R;
import com.amazaar.ListModels.OrderListModel;

import java.util.ArrayList;
import java.util.List;

public class TempListData {
    public List<ProductListModel> getHomeProductList() {

        List<ProductListModel> productListModelArrayList = new ArrayList<>();
        ProductListModel productListModel = new ProductListModel();

        productListModel.setProductImage(R.drawable.category_fruits_vegetables);
        productListModel.setItemType(ItemPbOuterClass.ItemTypeEnum.FRUITS);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_frozen_veg);
        productListModel.setItemType(ItemPbOuterClass.ItemTypeEnum.VEGETABLES);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_beverages);
        productListModel.setItemType(ItemPbOuterClass.ItemTypeEnum.KIRANA);
        productListModelArrayList.add(productListModel);

        productListModel = new ProductListModel();
        productListModel.setProductImage(R.drawable.category_branded_food);
        productListModel.setItemType(ItemPbOuterClass.ItemTypeEnum.DAIRY);
        productListModelArrayList.add(productListModel);

        /*productListModel = new ProductListModel();
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
        productListModelArrayList.add(productListModel);*/


        return productListModelArrayList;

    }

    public List<ProductListModel> getProductList() {

        List<ProductListModel> productListModelArrayList = new ArrayList<>();
        ProductListModel productListModel = new ProductListModel();

        productListModel.setProductName("Apple");
        productListModel.setProductPrice("$10 ");
        productListModel.setProductImage(R.drawable.p_apple);
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductName("Tomato");
        productListModel.setProductPrice("$15 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.p_tomato);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductName("Lemon");
        productListModel.setProductPrice("$25 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.p_lemon);
        productListModelArrayList.add(productListModel);

        productListModel = new ProductListModel();
        productListModel.setProductName("Pineapple");
        productListModel.setProductPrice("$8 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.pineple);
        productListModelArrayList.add(productListModel);


        productListModel.setProductName("Kiwi Fruit");
        productListModel.setProductPrice("$10 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.p_kiwi);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductName("Guava");
        productListModel.setProductPrice("$15 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.gw);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductName("Graps");
        productListModel.setProductPrice("$25 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.graps);
        productListModelArrayList.add(productListModel);

        productListModel = new ProductListModel();
        productListModel.setProductName("Pineapple");
        productListModel.setProductPrice("$8 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.pineple);
        productListModelArrayList.add(productListModel);

        productListModel.setProductName("Apple");
        productListModel.setProductPrice("$10 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.p_apple);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductName("Guava");
        productListModel.setProductPrice("$15 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.gw);
        productListModelArrayList.add(productListModel);


        productListModel = new ProductListModel();
        productListModel.setProductName("Graps");
        productListModel.setProductPrice("$25 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.graps);
        productListModelArrayList.add(productListModel);

        productListModel = new ProductListModel();
        productListModel.setProductName("Pineapple");
        productListModel.setProductPrice("$8 ");
        productListModel.setkG("1Kg");
        productListModel.setTotalKg(0);
        productListModel.setProductImage(R.drawable.pineple);
        productListModelArrayList.add(productListModel);


        return productListModelArrayList;

    }

//    public List<DiloagFitterItemModel> getFilterList() {
//
//        List<DiloagFitterItemModel> diloagFitterItemModelList = new ArrayList<>();
//        DiloagFitterItemModel diloagFitterItemModel = new DiloagFitterItemModel();
//
//        diloagFitterItemModel.setSorting("LOWEST");
//        diloagFitterItemModel.setPrice("PRICE");
//        diloagFitterItemModel.setFirst("FIRST");
//        diloagFitterItemModelList.add(diloagFitterItemModel);
//
//        diloagFitterItemModel = new DiloagFitterItemModel();
//        diloagFitterItemModel.setSorting("HEIGHEST");
//        diloagFitterItemModel.setPrice("PRICE");
//        diloagFitterItemModel.setFirst("FIRST");
//        diloagFitterItemModelList.add(diloagFitterItemModel);
//
//        diloagFitterItemModel = new DiloagFitterItemModel();
//        diloagFitterItemModel.setSorting("POPULER");
//        diloagFitterItemModel.setPrice("PRICE");
//        diloagFitterItemModel.setFirst("FIRST");
//        diloagFitterItemModelList.add(diloagFitterItemModel);
//
//        diloagFitterItemModel = new DiloagFitterItemModel();
//        diloagFitterItemModel.setSorting("NEWEST");
//        diloagFitterItemModel.setPrice("PRICE");
//        diloagFitterItemModel.setFirst("FIRST");
//        diloagFitterItemModelList.add(diloagFitterItemModel);
//
//        diloagFitterItemModel = new DiloagFitterItemModel();
//        diloagFitterItemModel.setSorting("BEST");
//        diloagFitterItemModel.setPrice("PRICE");
//        diloagFitterItemModel.setFirst("FIRST");
//        diloagFitterItemModelList.add(diloagFitterItemModel);
//
//        return diloagFitterItemModelList;
//
//    }

//    public List<DialogOfferListModel> getOfferList() {
//
//        List<DialogOfferListModel> diloagFitterItemModelList = new ArrayList<>();
//
//        DialogOfferListModel dialogOfferListModel = new DialogOfferListModel();
//
//        dialogOfferListModel = new DialogOfferListModel();
//        dialogOfferListModel.setOfferName("Buy More,\n" + "Save More");
//        diloagFitterItemModelList.add(dialogOfferListModel);
//
//        dialogOfferListModel = new DialogOfferListModel();
//        dialogOfferListModel.setOfferName("Special\n" + "Price");
//        diloagFitterItemModelList.add(dialogOfferListModel);
//
//        dialogOfferListModel = new DialogOfferListModel();
//        dialogOfferListModel.setOfferName("Item of\n" + "The Day");
//        diloagFitterItemModelList.add(dialogOfferListModel);
//
//        dialogOfferListModel = new DialogOfferListModel();
//        dialogOfferListModel.setOfferName("BUY 1,\n" + "GET 1 FREE");
//        diloagFitterItemModelList.add(dialogOfferListModel);
//
//        return diloagFitterItemModelList;
//
//    }
//
//    public List<DiloagFitterDiscountModel> getDiscountList() {
//
//        List<DiloagFitterDiscountModel> diloagFitterDiscountModels = new ArrayList<>();
//
//        DiloagFitterDiscountModel dialogOfferListModel = new DiloagFitterDiscountModel();
//
//        dialogOfferListModel = new DiloagFitterDiscountModel();
//        dialogOfferListModel.setDiscount("10");
//        diloagFitterDiscountModels.add(dialogOfferListModel);
//
//        dialogOfferListModel = new DiloagFitterDiscountModel();
//        dialogOfferListModel.setDiscount("20");
//        diloagFitterDiscountModels.add(dialogOfferListModel);
//
//        dialogOfferListModel = new DiloagFitterDiscountModel();
//        dialogOfferListModel.setDiscount("30");
//        diloagFitterDiscountModels.add(dialogOfferListModel);
//
//        dialogOfferListModel = new DiloagFitterDiscountModel();
//        dialogOfferListModel.setDiscount("40");
//        diloagFitterDiscountModels.add(dialogOfferListModel);
//
//        return diloagFitterDiscountModels;
//
//    }


//    public List<OrderListModelNew> getOrderListNewList() {
//
//        List<OrderListModelNew> orderListModelNewList = new ArrayList<>();
//
//        OrderListModelNew orderListModelNew = new OrderListModelNew();
//        orderListModelNew.setOrderName("Black Grape");
//        orderListModelNew.setQuntity("Qty: 1");
//        orderListModelNew.setPrice("₹ 122");
//        orderListModelNewList.add(orderListModelNew);
//
//        orderListModelNew = new OrderListModelNew();
//        orderListModelNew.setOrderName("Mango");
//        orderListModelNew.setQuntity("Qty: 1");
//        orderListModelNew.setPrice("₹ 100");
//        orderListModelNewList.add(orderListModelNew);
//
//        orderListModelNew = new OrderListModelNew();
//        orderListModelNew.setOrderName("Mango");
//        orderListModelNew.setQuntity("Qty: 1");
//        orderListModelNew.setPrice("₹ 100");
//        orderListModelNewList.add(orderListModelNew);
//
//
//        orderListModelNew = new OrderListModelNew();
//        orderListModelNew.setOrderName("Mango");
//        orderListModelNew.setQuntity("Qty: 1");
//        orderListModelNew.setPrice("₹ 100");
//        orderListModelNewList.add(orderListModelNew);
//
//        orderListModelNew = new OrderListModelNew();
//        orderListModelNew.setOrderName("Mango");
//        orderListModelNew.setQuntity("Qty: 1");
//        orderListModelNew.setPrice("₹ 100");
//        orderListModelNewList.add(orderListModelNew);
//
//
//        return orderListModelNewList;
//
//    }


//    public List<CartlistModel> getCartList() {
//
//        List<CartlistModel> cartlistModelNewList = new ArrayList<>();
//        CartlistModel productListModel = new CartlistModel();
//
//        productListModel.setProductName("Black Grape");
//        productListModel.setProductPrice(10);
//        productListModel.setProductImages(R.drawable.p_black_graps);
//        productListModel.setKg("1Kg");
//        productListModel.setProductQuantity(1);
//        cartlistModelNewList.add(productListModel);
//
//
//        productListModel = new CartlistModel();
//        productListModel.setProductName("Tomato");
//        productListModel.setProductPrice(50);
//        productListModel.setProductImages(R.drawable.p_tomato);
//        productListModel.setKg("1Kg");
//        productListModel.setProductQuantity(1);
//        cartlistModelNewList.add(productListModel);
//
//        productListModel = new CartlistModel();
//        productListModel.setProductName("Mango");
//        productListModel.setProductPrice(30);
//        productListModel.setProductImages(R.drawable.p_mango);
//        productListModel.setKg("1Kg");
//        productListModel.setProductQuantity(1);
//        cartlistModelNewList.add(productListModel);
//
//        productListModel = new CartlistModel();
//        productListModel.setProductName("Capsicum");
//        productListModel.setProductPrice(15);
//        productListModel.setProductImages(R.drawable.p_capsecum);
//        productListModel.setKg("1Kg");
//        productListModel.setProductQuantity(1);
//        cartlistModelNewList.add(productListModel);
//
//
//        return cartlistModelNewList;
//
//
//    }


    public List<OrderListModel> getOrderList() {

        List<OrderListModel> orderListModelList = new ArrayList<>();


        OrderListModel OrderListModelNew = new OrderListModel();
        OrderListModelNew.setOrderId("#1122GG33");
        OrderListModelNew.setPrice("$10 ");
        OrderListModelNew.setOrderDate("26-12-2017");
        OrderListModelNew.setDeliveryDate("28-12-2017");
        OrderListModelNew.setStatus("pending");
        orderListModelList.add(OrderListModelNew);


        OrderListModelNew = new OrderListModel();
        OrderListModelNew.setOrderId("#3322GG33");
        OrderListModelNew.setPrice("$10 ");
        OrderListModelNew.setOrderDate("28-11-2017");
        OrderListModelNew.setDeliveryDate("30-11-2017");
        OrderListModelNew.setStatus("cancel");
        orderListModelList.add(OrderListModelNew);

        OrderListModelNew = new OrderListModel();
        OrderListModelNew.setOrderId("#4589GG32");
        OrderListModelNew.setPrice("$10 ");
        OrderListModelNew.setOrderDate("05-12-2017");
        OrderListModelNew.setDeliveryDate("07-12-2017");
        OrderListModelNew.setStatus("delivery");
        orderListModelList.add(OrderListModelNew);


        OrderListModelNew = new OrderListModel();
        OrderListModelNew.setOrderId("#1522O833");
        OrderListModelNew.setPrice("$10 ");
        OrderListModelNew.setOrderDate("23-08-2017");
        OrderListModelNew.setDeliveryDate("24-28-2017");
        OrderListModelNew.setStatus("pending");
        orderListModelList.add(OrderListModelNew);


        OrderListModelNew = new OrderListModel();
        OrderListModelNew.setOrderId("#8922HJ78");
        OrderListModelNew.setPrice("$10 ");
        OrderListModelNew.setOrderDate("08-05-2017");
        OrderListModelNew.setDeliveryDate("10-05-2017");
        OrderListModelNew.setStatus("delivery");
        orderListModelList.add(OrderListModelNew);


        OrderListModelNew = new OrderListModel();
        OrderListModelNew.setOrderId("#1256GG34");
        OrderListModelNew.setPrice("$10 ");
        OrderListModelNew.setOrderDate("08-08-2017");
        OrderListModelNew.setDeliveryDate("10-08-2017");
        OrderListModelNew.setStatus("cancel");
        orderListModelList.add(OrderListModelNew);


        return orderListModelList;


    }


//    public List<AddressListModelNew> getCheckoutAddress() {
//
//        AddressListModelNew addressListModelNew = new AddressListModelNew();
//        List<AddressListModelNew> addressListModelNews = new ArrayList<>();
//
//        addressListModelNew.setAddress("Hans Egedesvej 29\n" + "P.O. Box 1615\n" + "3900 Nuuk\n" + "Greenland");
//        addressListModelNews.add(addressListModelNew);
//
//        addressListModelNew = new AddressListModelNew();
//        addressListModelNew.setAddress("Hans Egedesvej 29\n" + "P.O. Box 1615\n" + "3900 Nuuk\n" + "Greenland");
//        addressListModelNews.add(addressListModelNew);
//
//
//        return addressListModelNews;
//
//
//    }

}
