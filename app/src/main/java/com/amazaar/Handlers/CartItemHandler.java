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
        String[] splitIds = data.getPbModel().getDbInfo().getId().split("@");
        CartPbOuterClass.CartPb.Builder builder = CartPbOuterClass.CartPb.newBuilder();
        builder.setItem(data.getPbModel());
        builder.setItemKey(Integer.parseInt(splitIds[0]));
        builder.setQuantity(data.getTotalKg());
        CartEntity cartEntity = new CartEntity();
        cartEntity.setmId((long) Integer.parseInt(splitIds[0]));
        cartEntity.setEid(builder.getItem().getDbInfo().getId());
        try {
            m_cartEntityDaoHelper.getDeoEntity().insertOrReplace(m_cartEntityDaoHelper.fromPb(cartEntity, builder.build()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handle(CartListModel data) {
        CartEntity cartEntity = new CartEntity();
        String[] splitIds = data.getOnitemChange().getData().getItem().getDbInfo().getId().split("@");
        cartEntity.setmId((long) Integer.parseInt(splitIds[0]));
        cartEntity.setEid(data.getOnitemChange().getData().getItem().getDbInfo().getId());
        CartPbOuterClass.CartPb.Builder builder = CartPbOuterClass.CartPb.newBuilder(data.getOnitemChange().getData());
        builder.setItemKey(Integer.parseInt(splitIds[0]));
        try {
            m_cartEntityDaoHelper.getDeoEntity().insertOrReplace(m_cartEntityDaoHelper.fromPb(cartEntity, builder.build()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteItemFromCart(int position) {
        m_cartEntityDaoHelper.getDeoEntity().deleteByKey((long) position);
    }

    public void deleteAllItemFromCart() {
        m_cartEntityDaoHelper.getDeoEntity().deleteAll();
    }

    public void deleteItemFromCart(CartPbOuterClass.CartPb cartPb) {
        CartEntity cartEntity = new CartEntity();
        cartEntity.setEid(cartPb.getItem().getDbInfo().getId());
        cartEntity.setmId(cartPb.getItemKey());
        try {
            m_cartEntityDaoHelper.getDeoEntity().delete(m_cartEntityDaoHelper.fromPb(cartEntity, cartPb));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
