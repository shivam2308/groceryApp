package com.amazaar.Widget.CartWidget;

import com.amazaar.Adapters.CartListAdapter;
import com.amazaar.Convertors.CartPbToCartListModelConvertor;
import com.amazaar.DatabaseEnitityHelper.CartEntityDaoHelper;
import com.amazaar.Fragments.CartListFragment;
import com.amazaar.ListModels.CartListModel;
import com.amazaar.Protobuff.CartPbOuterClass;

import java.util.List;

import javax.inject.Inject;

public class CartView {

    private CartListAdapter productListAdapter;
    private CartListFragment m_mainFragment;
    private List<CartListModel> productListModelArrayList;
    @Inject
    public CartEntityDaoHelper m_cartEntityDeo;
    @Inject
    public CartPbToCartListModelConvertor m_cartPbToCartListModelConvertor;

    @Inject
    public CartView() {

    }

    public CartListFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(CartListFragment fragment) {
        m_mainFragment = fragment;
    }

    public List<CartListModel> getCartListModel() {
        return productListModelArrayList;
    }

    public void setCartListModel(List<CartListModel> listModel) {
        productListModelArrayList = listModel;
    }

    public CartListAdapter getCartListAdapter() {
        return productListAdapter;
    }

    public void setCartListAdapter(CartListAdapter adapter) {
        productListAdapter = adapter;
    }


    public String getTotalPrice(List<CartListModel> listModel) {
        float total = 0;
        for (CartListModel model: listModel) {
            total+=model.getOnitemChange().getData().getQuantity()*model.getOnitemChange().getData().getItem().getPrice();
        }
        return String.valueOf(total);
    }

    public void getCartList() {
        getCartListModel().clear();
        getCartListModel().addAll(m_cartPbToCartListModelConvertor.getListModel(m_cartEntityDeo.getCartListPb()));
    }

    public CartPbOuterClass.CartPb getUpdatedCartItem(CartPbOuterClass.CartPb data, int totalKg) {
        CartPbOuterClass.CartPb.Builder newPb = CartPbOuterClass.CartPb.newBuilder(data);
        newPb.setQuantity(totalKg);
        return newPb.build();
    }
}
