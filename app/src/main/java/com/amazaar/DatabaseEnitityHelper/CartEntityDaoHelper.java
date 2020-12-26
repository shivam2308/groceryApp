package com.amazaar.DatabaseEnitityHelper;

import android.content.Context;

import com.amazaar.Interfaces.IDatabaseEntity;
import com.amazaar.LocalDatabase.AEnityToPbConvertor;
import com.amazaar.LocalDatabase.CartEntityDao;
import com.amazaar.LocalDatabase.DaoSession;
import com.amazaar.LocalDatabase.DatabaseInitHandler;
import com.amazaar.LocalDatabase.Enity.CartEntity;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.CartPbOuterClass;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import java.io.IOException;
import java.util.List;

import javax.inject.Inject;

public class CartEntityDaoHelper extends AEnityToPbConvertor<CartEntity, CartPbOuterClass.CartPb> implements IDatabaseEntity<CartEntityDao, CartEntity, CartPbOuterClass.CartPb> {

    private DatabaseInitHandler m_databaseInitHandler;
    private DaoSession m_daoSession;
    private Context m_context;
    private RequestQueue m_RequestQueue;
    private CartEntityDao m_cartEntityDao;

    @Inject
    public CartEntityDaoHelper() {
        m_context = AmazaarApplication.getContext();
        init();
    }

    private void init() {
        m_RequestQueue = Volley.newRequestQueue(m_context);
        m_databaseInitHandler = new DatabaseInitHandler();
        m_databaseInitHandler.initDataBase(m_context);
        m_daoSession = m_databaseInitHandler.getDaoSession();
        m_cartEntityDao = m_daoSession.getCartEntityDao();
    }

    @Override
    public CartEntityDao getDeoEntity() {
        return m_cartEntityDao;
    }

    @Override
    public DaoSession getDeoSession() {
        return m_daoSession;
    }

    @Override
    public CartEntity toEntity(CartEntity data, CartPbOuterClass.CartPb message) throws IOException {
        return fromPb(data, message);
    }

    @Override
    public CartPbOuterClass.CartPb toPB(CartEntity entity) throws IOException {
        if (entity != null) {
            return fromEntity(entity, CartPbOuterClass.CartPb.class);
        } else {
            return CartPbOuterClass.CartPb.getDefaultInstance();
        }
    }


    public CartPbOuterClass.CartPb getLoginPbFromInternalStorage(long id) throws IOException {
        return toPB(getDeoEntity().load(id));
    }

    public CartPbOuterClass.CartListPb getCartListPb() {
        CartPbOuterClass.CartListPb.Builder cartList = CartPbOuterClass.CartListPb.newBuilder();
        List<CartEntity> list = getDeoEntity().loadAll();
        for (CartEntity item : list) {
            try {
                CartPbOuterClass.CartPb.Builder builder= CartPbOuterClass.CartPb.newBuilder();
                builder.setItemKey(item.getmId());
                builder.setItem(toPB(item).getItem());
                builder.setQuantity(toPB(item).getQuantity());
                cartList.addCartItem(builder.build());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return cartList.build();
    }
}