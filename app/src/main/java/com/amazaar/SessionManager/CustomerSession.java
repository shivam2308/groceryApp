package com.amazaar.SessionManager;

import com.amazaar.CommonCode.ASession;
import com.amazaar.Protobuff.CustomerPbOuterClass;

import javax.inject.Inject;

public class CustomerSession extends ASession<CustomerPbOuterClass.CustomerPb> {

    @Inject
    public CustomerSession() {
        super("customer", CustomerPbOuterClass.CustomerPb.class);
    }
}
