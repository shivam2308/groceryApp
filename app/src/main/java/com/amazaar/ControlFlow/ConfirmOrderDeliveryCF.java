package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.ConfirmOrderDeliveryClientService;
import com.amazaar.CommonCode.AToast;
import com.amazaar.Enums.CallSyncStateEnum;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.Protobuff.ConfirmOrderDeliveryPbOuterClass;
import com.prod.basic.common.async.AControlFlow;

import java.util.concurrent.ExecutionException;

public class ConfirmOrderDeliveryCF extends AControlFlow<ConfirmOrderDeliveryCF.State, Void, Exception> {

    private ConfirmOrderDeliveryClientService m_clientService;
    private ConfirmOrderDeliveryPbOuterClass.ConfirmOrderDeliveryPb m_confirmOrderDeliveryPb;
    private VariableValueChange<CallSyncStateEnum> m_syncState;

    public ConfirmOrderDeliveryCF(ConfirmOrderDeliveryPbOuterClass.ConfirmOrderDeliveryPb confirmOrderDeliveryPb, ConfirmOrderDeliveryClientService clientService, VariableValueChange<CallSyncStateEnum> syncState) {
        super(State.SET_SYNC_STATE, State.DONE);
        m_clientService = clientService;
        m_confirmOrderDeliveryPb = confirmOrderDeliveryPb;
        m_syncState = syncState;
        addStateHandler(State.SET_SYNC_STATE, new SetSyncStateHandler());
        addStateHandler(State.PERFORM_CALL, new PerformCallHandler());
        addStateHandler(State.VALIDATE_RESP, new ValidateRespHandler());
    }


    enum State {
        SET_SYNC_STATE,
        PERFORM_CALL,
        VALIDATE_RESP,
        DONE
    }

    private class SetSyncStateHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            m_syncState.setVar(CallSyncStateEnum.SYNC_START);
            return State.PERFORM_CALL;
        }
    }

    private class PerformCallHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {
            try {
                m_confirmOrderDeliveryPb = m_clientService.create(m_confirmOrderDeliveryPb);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public State handleState() {
            return State.VALIDATE_RESP;
        }
    }

    private class ValidateRespHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            if (m_confirmOrderDeliveryPb != null) {
                if (m_confirmOrderDeliveryPb.getStatus() == BuyPbOuterClass.IsOrderedPlacedEnum.ORDER_SUCCESS) {
                    AToast.orderDeliveredSuccess();
                } else {
                    AToast.orderDeliverdFailed();
                }
            } else {
                return State.DONE;
            }
            m_syncState.setVar(CallSyncStateEnum.SYNC_DONE);
            return State.DONE;
        }
    }
}
