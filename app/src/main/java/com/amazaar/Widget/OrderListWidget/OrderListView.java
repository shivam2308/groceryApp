package com.amazaar.Widget.OrderListWidget;

import com.amazaar.ClientServices.OrderedListClientService;
import com.amazaar.Fragments.OrderListFragment;
import com.amazaar.ListModels.OrderListModel;
import com.amazaar.Protobuff.OrderedListPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class OrderListView {
    public OrderedListClientService m_orderListClientService;
    public CustomerSession m_customerSesssion;
    private OrderListFragment m_mainFragment;

    @Inject
    public OrderListView(OrderedListClientService orderListClientService, CustomerSession customerSesssion) {
        m_orderListClientService = orderListClientService;
        m_customerSesssion = customerSesssion;
    }

    public OrderListFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(OrderListFragment fragment) {
        m_mainFragment = fragment;
    }

    public List<OrderListModel> getOrderlistResp() {
        OrderedListPbOuterClass.OrderedListSearchReqPb.Builder request = OrderedListPbOuterClass.OrderedListSearchReqPb.newBuilder();
        request.setCustomerId(m_customerSesssion.getSession().getDbInfo().getId());
        try {
            return getOrderListModel(m_orderListClientService.search(request.build()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    private List<OrderListModel> getOrderListModel(OrderedListPbOuterClass.OrderedListSearchRespPb resp) {
        List<OrderListModel> list = new ArrayList<>();
        for (OrderedListPbOuterClass.OrderedListPb oreder:resp.getOrderListresultsList()) {
            OrderListModel model = new OrderListModel();
            model.getonOrderChange().setVar(oreder);
            list.add(model);
        }
        return list;
    }
}
