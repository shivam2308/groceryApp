package com.amazaar.Widget.OrderSummaryWidget;

import com.amazaar.ClientServices.OrderedListClientService;
import com.amazaar.Fragments.OrderSummaryFragment;
import com.amazaar.ListModels.OrderListModel;
import com.amazaar.ListModels.OrderSummaryListModel;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.Protobuff.OrderedListPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

//import com.amazaar.Fragments.OrderSummaryFragment;

public class OrderSummaryView {

    public OrderedListClientService m_orderListClientService;
    public VariableValueChange<OrderListModel> m_orderParentId;
    public CustomerSession m_cutomerSession;
    private OrderSummaryFragment m_mainFragment;
    private List<OrderSummaryListModel> m_orderSummary = new ArrayList<>();

    @Inject
    public OrderSummaryView(VariableValueChange<OrderListModel> orderParentId, OrderedListClientService orderListClientService, CustomerSession cutomerSession) {
        m_orderParentId = orderParentId;
        m_orderListClientService = orderListClientService;
        m_cutomerSession = cutomerSession;
        getOrderParentId().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {

            }
        });

    }

    public CustomerSession getCustomerSession() {
        return m_cutomerSession;
    }

    public void startOrderGetList() {
        OrderedListPbOuterClass.OrderedListSearchReqPb.Builder builder = OrderedListPbOuterClass.OrderedListSearchReqPb.newBuilder();
        builder.setOrderParentId(getOrderParentId().getVar().getOrderId());
        try {
            getOrderSummaryResp(m_orderListClientService.search(builder.build()));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void getOrderSummaryResp(OrderedListPbOuterClass.OrderedListSearchRespPb resp) {
        for (BuyPbOuterClass.BuyPb buyPb : resp.getBuyListresultsList()) {
            OrderSummaryListModel model = new OrderSummaryListModel();
            model.getOnitemChange().setVar(buyPb);
            m_orderSummary.add(model);
        }
    }

    public VariableValueChange<OrderListModel> getOrderParentId() {
        return m_orderParentId;
    }

    public OrderSummaryFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(OrderSummaryFragment fragment) {
        m_mainFragment = fragment;
    }

    public List<OrderSummaryListModel> getOrderSummaryListModel() {
        return m_orderSummary;
    }


}
