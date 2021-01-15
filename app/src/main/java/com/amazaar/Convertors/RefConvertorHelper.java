package com.amazaar.Convertors;

import com.amazaar.Protobuff.CustomerPbOuterClass;

import javax.inject.Inject;

public class RefConvertorHelper {

    @Inject
    public RefConvertorHelper(){

    }

    public CustomerPbOuterClass.CustomerPbRef getCustomerRef(CustomerPbOuterClass.CustomerPb customerPb){
        CustomerPbOuterClass.CustomerPbRef.Builder builder = CustomerPbOuterClass.CustomerPbRef.newBuilder();
        builder.setId(customerPb.getDbInfo().getId());
        builder.setName(customerPb.getName());
        builder.setContact(customerPb.getContact());
        return builder.build();
    }
}
