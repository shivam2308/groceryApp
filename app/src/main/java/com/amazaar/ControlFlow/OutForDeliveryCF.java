package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.BuyClientService;
import com.amazaar.ClientServices.CloseAndOutForDeliveryClientService;
import com.amazaar.CommonCode.AToast;
import com.amazaar.Enums.CallSyncStateEnum;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.code.Strings;

import java.util.concurrent.ExecutionException;

public class OutForDeliveryCF extends AControlFlow<OutForDeliveryCF.State, Void, Exception> {

    private BuyClientService m_buyClientService;
    private CloseAndOutForDeliveryClientService m_CloseAndOutForDeliveryClientService;
    private String m_req;

    public OutForDeliveryCF(String req, BuyClientService buyClientService, CloseAndOutForDeliveryClientService closeAndOutForDeliveryClientService) {
        super(State.GET_BUY_PRODUCT_CHECK_DELIVERY_REF, State.DONE);
        m_buyClientService = buyClientService;
        m_req = req;
        m_CloseAndOutForDeliveryClientService = closeAndOutForDeliveryClientService;
        addStateHandler(State.GET_BUY_PRODUCT_CHECK_DELIVERY_REF, new GetProductHandler());
        addStateHandler(State.OUT_FOR_DELIVERY, new OutForDeliveryHandler());
    }


    enum State {
        GET_BUY_PRODUCT_CHECK_DELIVERY_REF,
        OUT_FOR_DELIVERY,
        DONE
    }

    private class GetProductHandler implements AControlFlow.StateHandler<State> {
        BuyPbOuterClass.BuySearchResponsePb resp;
        @Override
        public void registerCalls() {
            BuyPbOuterClass.BuySearchRequestPb.Builder builder = BuyPbOuterClass.BuySearchRequestPb.newBuilder();
            builder.setParentOrderId(m_req);
            try {
                resp = m_buyClientService.search(builder.build());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public State handleState() {
            if(resp.getSummary().getResultCount()>0){
                for (BuyPbOuterClass.BuyPb pb:resp.getResultsList()) {
                    if(Strings.isEmpty(pb.getDeliveryManRef().getId())){
                        AToast.setDeliveryMan();
                        return State.DONE;
                    }
                }
                return State.OUT_FOR_DELIVERY;
            }else{
                return State.DONE;
            }
        }
    }

    private class OutForDeliveryHandler implements AControlFlow.StateHandler<State> {
        @Override
        public void registerCalls() {
            try {
                m_CloseAndOutForDeliveryClientService.get(m_req+ "#" + BuyPbOuterClass.DeliveryStatusEnum.OUT_FOR_DELIVERY.name());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public State handleState() {
            return State.DONE;
        }
    }

}
