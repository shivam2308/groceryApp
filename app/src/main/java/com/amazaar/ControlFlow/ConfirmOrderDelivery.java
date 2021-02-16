package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.ConfirmOrderDeliveryClientService;
import com.amazaar.Enums.CallSyncStateEnum;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Protobuff.ConfirmOrderDeliveryPbOuterClass;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class ConfirmOrderDelivery {

    private ConfirmOrderDeliveryClientService m_clientServices;
    private VariableValueChange<CallSyncStateEnum> m_callSync;

    @Inject
    public ConfirmOrderDelivery(ConfirmOrderDeliveryClientService clientServices, VariableValueChange<CallSyncStateEnum> callSync) {
        m_clientServices = clientServices;
        m_callSync = callSync;
    }

    public VariableValueChange<CallSyncStateEnum> getCallSync() {
        return m_callSync;
    }

    public IFuture<Void, Exception> confirmOrder(ConfirmOrderDeliveryPbOuterClass.ConfirmOrderDeliveryPb confirmOrderDeliveryPb) {
        ConfirmOrderDeliveryCF cf = new ConfirmOrderDeliveryCF(confirmOrderDeliveryPb, m_clientServices, m_callSync);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();
    }
}
