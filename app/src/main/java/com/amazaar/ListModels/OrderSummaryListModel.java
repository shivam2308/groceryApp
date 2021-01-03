package com.amazaar.ListModels;

import com.amazaar.CommonCode.DataModel;
import com.amazaar.DefaultProviders.BuyPbDefaultProvider;
import com.amazaar.Protobuff.BuyPbOuterClass;

import javax.inject.Inject;

public class OrderSummaryListModel {
    public DataModel<BuyPbOuterClass.BuyPb, BuyPbDefaultProvider> m_onitemChange;
    private String orderId;
    private String orderName;
    private String quntity;
    private String price;

    @Inject
    public OrderSummaryListModel() {
        m_onitemChange = new DataModel<>(new BuyPbDefaultProvider());
        m_onitemChange.setListener(new DataModel.ChangeListener() {
            @Override
            public void onChange() {
                setOrderId(m_onitemChange.getData().getOrderId());
                setOrderName(m_onitemChange.getData().getItemRef().getItemName().getFirstName());
                setQuntity(String.valueOf(m_onitemChange.getData().getQuantity()));
                setPrice(String.valueOf(m_onitemChange.getData().getQuantity() * m_onitemChange.getData().getItemRef().getPrice()));
            }
        });
    }

    public DataModel<BuyPbOuterClass.BuyPb, BuyPbDefaultProvider> getOnitemChange() {
        return m_onitemChange;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
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
