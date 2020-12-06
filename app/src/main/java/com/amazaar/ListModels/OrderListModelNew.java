package com.amazaar.ListModels;

import com.amazaar.CommonCode.DataModel;
import com.amazaar.DefaultProviders.CartPbDefaultProvider;
import com.amazaar.Protobuff.CartPbOuterClass;

import javax.inject.Inject;

/**
 * Created by Nishish on 8/6/2018.
 */

public class OrderListModelNew {
    public DataModel<CartPbOuterClass.CartPb, CartPbDefaultProvider> m_onitemChange;
    private String orderName;
    private String quntity;
    private String price;

    @Inject
    public OrderListModelNew() {
        m_onitemChange = new DataModel<>(new CartPbDefaultProvider());
        m_onitemChange.setListener(new DataModel.ChangeListener() {
            @Override
            public void onChange() {
                setOrderName(m_onitemChange.getData().getItem().getItemName().getFirstName());
                setQuntity(String.valueOf(m_onitemChange.getData().getQuantity()));
                setPrice(String.valueOf(m_onitemChange.getData().getQuantity() * m_onitemChange.getData().getItem().getPrice()));
            }
        });
    }

    public DataModel<CartPbOuterClass.CartPb, CartPbDefaultProvider> getOnitemChange() {
        return m_onitemChange;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getQuntity() {
        return quntity;
    }

    public void setQuntity(String quntity) {
        this.quntity = quntity;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
