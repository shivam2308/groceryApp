package com.amazaar.Handlers;

import com.amazaar.DatabaseEnitityHelper.CartEntityDaoHelper;
import com.amazaar.ListModels.CartListModel;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.LocalDatabase.Enity.CartEntity;
import com.amazaar.Protobuff.CartPbOuterClass;

import javax.inject.Inject;

public class CartItemHandler {

    @Inject
    private CartEntityDaoHelper m_cartEntityDaoHelper;

    @Inject
    public CartItemHandler(CartEntityDaoHelper cartEntityDaoHelper) {
        m_cartEntityDaoHelper = cartEntityDaoHelper;
    }

    public void handle(ProductListModel data, int position) {
        CartPbOuterClass.CartPb.Builder builder = CartPbOuterClass.CartPb.newBuilder();
        builder.setItem(data.getPbModel());
        builder.setQuantity(data.getTotalKg());
        CartEntity cartEntity = new CartEntity();
        cartEntity.setmId((long) position);
        cartEntity.setEid(builder.getItem().getDbInfo().getId());
        try {
            m_cartEntityDaoHelper.getDeoEntity().insertOrReplace(m_cartEntityDaoHelper.fromPb(cartEntity, builder.build()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handle(CartListModel data, int position) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setmId((long) position);
        cartEntity.setEid(data.getOnitemChange().getData().getItem().getDbInfo().getId());
        try {
            m_cartEntityDaoHelper.getDeoEntity().insertOrReplace(m_cartEntityDaoHelper.fromPb(cartEntity, data.getOnitemChange().getData()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteItemFromCart(int position) {
        m_cartEntityDaoHelper.getDeoEntity().deleteByKey((long) position);
    }

    public void deleteItemFromCart(CartPbOuterClass.CartPb cartPb) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setEid(cartPb.getItem().getDbInfo().getId());
        try {
            m_cartEntityDaoHelper.getDeoEntity().delete(m_cartEntityDaoHelper.fromPb(cartEntity, cartPb));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
