package com.amazaar.Convertors;

import com.amazaar.Interfaces.IPbToModelConvertor;
import com.amazaar.ListModels.CartListModel;
import com.amazaar.ListModels.OrderListModelNew;
import com.amazaar.Protobuff.CartPbOuterClass;
import com.prod.basic.common.collect.Lists;

import java.util.List;

import javax.inject.Inject;

public class CartPbToCartListModelConvertor implements IPbToModelConvertor<CartPbOuterClass.CartListPb, List<CartListModel>> {

    @Inject
    public CartPbToCartListModelConvertor() {

    }

    @Override
    public List<CartListModel> getListModel(CartPbOuterClass.CartListPb respPb) {
        List<CartListModel> cartList = Lists.newArrayList();
        for (CartPbOuterClass.CartPb item : respPb.getCartItemList()) {
            if (item.getQuantity() > 0) {
                CartListModel model = new CartListModel();
                model.getOnitemChange().setVar(item);
                cartList.add(model);
            }
        }
        return cartList;
    }

    public List<OrderListModelNew> getOrderListModel(CartPbOuterClass.CartListPb resppb) {
        List<OrderListModelNew> cartList = Lists.newArrayList();
        for (CartPbOuterClass.CartPb item : resppb.getCartItemList()) {
            if (item.getQuantity() > 0) {
                OrderListModelNew model = new OrderListModelNew();
                model.getOnitemChange().setVar(item);
                cartList.add(model);
            }
        }
        return cartList;
    }
}
