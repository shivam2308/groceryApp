package com.amazaar.Helpers;

import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.prod.basic.common.code.Strings;

import javax.inject.Inject;

public class CustomerHelper {

    @Inject
    public CustomerHelper() {

    }


    public CustomerPbOuterClass.CustomerPb getCustomerUpdatedPb(CustomerPbOuterClass.CustomerPb oldPb, CustomerPbOuterClass.CustomerPb newPb) {
        CustomerPbOuterClass.CustomerPb.Builder builder =  CustomerPbOuterClass.CustomerPb.newBuilder(newPb);
        builder.setDbInfo(oldPb.getDbInfo());
        return builder.build();
    }
}
