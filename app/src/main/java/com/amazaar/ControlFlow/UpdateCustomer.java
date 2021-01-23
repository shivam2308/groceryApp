package com.amazaar.ControlFlow;


import com.amazaar.ClientServices.CustomerClientService;
import com.amazaar.Helpers.CustomerHelper;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class UpdateCustomer {
    private CustomerClientService m_reqService;
    private CustomerSession m_customerSession;
    private CustomerHelper m_customerHelper;

    @Inject
    public UpdateCustomer(CustomerClientService reqService, CustomerSession customerSession,CustomerHelper customerHelper) {
        m_reqService = reqService;
        m_customerSession = customerSession;
        m_customerHelper=customerHelper;
    }


    public IFuture<CustomerPbOuterClass.CustomerPb, Exception> updateCustomer(CustomerPbOuterClass.CustomerPb request) {
        UpdateCustomerCF cf = new UpdateCustomerCF(request, m_reqService, m_customerSession,m_customerHelper);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();

    }
}
