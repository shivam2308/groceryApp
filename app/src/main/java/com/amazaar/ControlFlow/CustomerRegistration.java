package com.amazaar.ControlFlow;

import android.content.Context;

import com.amazaar.ClientServices.RegistrationClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Protobuff.RegistrationPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class CustomerRegistration {

    private RegistrationClientService m_reqService;
    private LoginEntityDaoHelper m_loLoginEntityDaoHelper;
    private CustomerSession m_customerSession;

    @Inject
    public CustomerRegistration(RegistrationClientService reqService, LoginEntityDaoHelper loLoginEntityDaoHelper, CustomerSession customerSession) {
        m_reqService = reqService;
        m_loLoginEntityDaoHelper = loLoginEntityDaoHelper;
        m_customerSession = customerSession;
    }


    public IFuture<RegistrationPbOuterClass.RegistrationPb, Exception> startRegistration(Context context, RegistrationPbOuterClass.RegistrationPb request) {
        CustomerRegistrationCF cf = new CustomerRegistrationCF(context, request, m_reqService, m_loLoginEntityDaoHelper, m_customerSession);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();

    }
}
