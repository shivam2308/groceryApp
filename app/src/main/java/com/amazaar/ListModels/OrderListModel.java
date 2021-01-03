package com.amazaar.ListModels;

import com.amazaar.CommonCode.DataModel;
import com.amazaar.DefaultProviders.OrderedListDefaultPbProvider;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.Protobuff.OrderedListPbOuterClass;

import javax.inject.Inject;

public class OrderListModel {
    private String orderId;
    private String price;
    private String orderDate;
    private String deliveryDate;
    private String proName;
    private BuyPbOuterClass.DeliveryStatusEnum status;
    public DataModel<OrderedListPbOuterClass.OrderedListPb, OrderedListDefaultPbProvider> m_onOrderChange;


    @Inject
    public OrderListModel(){
        m_onOrderChange = new DataModel<>(new OrderedListDefaultPbProvider());
        m_onOrderChange.setListener(new DataModel.ChangeListener() {
            @Override
            public void onChange() {
                setOrderId(m_onOrderChange.getData().getParentOrderId());
                setPrice(String.valueOf(m_onOrderChange.getData().getPaymentRef().getAmount()));
                setStatus(m_onOrderChange.getData().getDeliveryStatus());
                setOrderDate(m_onOrderChange.getData().getTime().getFormattedDate());
            }
        });
    }

    public DataModel<OrderedListPbOuterClass.OrderedListPb, OrderedListDefaultPbProvider> getonOrderChange() {
        return m_onOrderChange;
    }


    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(String deliveryDate) {
        this.deliveryDate = deliveryDate;
    }



    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public BuyPbOuterClass.DeliveryStatusEnum getStatus() {
        return status;
    }

    public void setStatus(BuyPbOuterClass.DeliveryStatusEnum status) {
        this.status = status;
    }
}
