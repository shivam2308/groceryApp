package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class CustomerClientService extends AClientService<CustomerPbOuterClass.CustomerPb, CustomerPbOuterClass.CustomerSearchRequestPb, CustomerPbOuterClass.CustomerSearchResponsePb> {

    @Inject
    public CustomerClientService() {
        super(CustomerPbOuterClass.CustomerPb.class, CustomerPbOuterClass.CustomerSearchResponsePb.class, UrlPathProvider.UrlPathEnum.CUSTOMER);
    }
}
