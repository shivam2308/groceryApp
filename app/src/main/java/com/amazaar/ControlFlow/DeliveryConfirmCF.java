package com.amazaar.ControlFlow;

import com.prod.basic.common.async.AControlFlow;

public class DeliveryConfirmCF extends AControlFlow<DeliveryConfirmCF.State, Void, Exception> {

    protected DeliveryConfirmCF() {
        super(State.GIVE_CONFIRM_REQUEST, State.DONE);
    }

    enum State {
        GIVE_CONFIRM_REQUEST,
        DONE,
    }
}
