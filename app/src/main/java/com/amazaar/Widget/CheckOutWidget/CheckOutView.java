package com.amazaar.Widget.CheckOutWidget;

import com.amazaar.Convertors.CartPbToCartListModelConvertor;
import com.amazaar.DatabaseEnitityHelper.CartEntityDaoHelper;
import com.amazaar.Fragments.CheckOutFragment;
import com.amazaar.ListModels.AddressListModelNew;
import com.amazaar.ListModels.OrderListModelNew;
import com.amazaar.SessionManager.CustomerSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class CheckOutView {

    @Inject
    public CartEntityDaoHelper m_cartEntityDeo;
    @Inject
    public CartPbToCartListModelConvertor m_cartPbToCartListModelConvertor;
    @Inject
    public CustomerSession m_session;
    @Inject
    private CheckOutFragment m_mainFragment;
    private List<AddressListModelNew> addressListModelNewList;
    private List<OrderListModelNew> orderListModelNewList;

    @Inject
    public CheckOutView(CustomerSession session, CartEntityDaoHelper cartEntityDeo, CartPbToCartListModelConvertor cartPbToCartListModelConvertor) {
        m_cartEntityDeo = cartEntityDeo;
        m_session = session;
        m_cartPbToCartListModelConvertor = cartPbToCartListModelConvertor;
        addressListModelNewList = new ArrayList<>();
        orderListModelNewList = new ArrayList<>();
        setAddrrss();
        setOrder();
    }

    public void setOrder() {
        getOrderListModel().clear();
        getOrderListModel().addAll(m_cartPbToCartListModelConvertor.getOrderListModel(m_cartEntityDeo.getCartListPb()));
    }

    private void setAddrrss() {
        AddressListModelNew model = new AddressListModelNew();
        model.getAddressModel().setVar(m_session.getSession().getAddress());
        getAddressListModel().add(model);
    }

    public CheckOutFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(CheckOutFragment checkOutFragment) {
        m_mainFragment = checkOutFragment;
    }

    public List<AddressListModelNew> getAddressListModel() {
        return addressListModelNewList;
    }

    public List<OrderListModelNew> getOrderListModel() {
        return orderListModelNewList;
    }

    public String getTotalPrice(){
        setOrder();
        float total = 0;
        for (OrderListModelNew model:getOrderListModel()) {
            total+=Float.parseFloat(model.getPrice());
        }
        return String.valueOf(total);
    }
}
