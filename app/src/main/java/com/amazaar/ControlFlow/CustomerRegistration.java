package com.amazaar.ControlFlow;

import android.content.Context;

import com.amazaar.ClientServices.RegistrationClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.RegistrationPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class CustomerRegistration {

    private RegistrationClientService m_reqService;
    private LoginEntityDaoHelper m_loLoginEntityDaoHelper;
    private CustomerSession m_customerSession;
    private RegisterPushNorification m_registerPushnotification;

    @Inject
    public CustomerRegistration(RegistrationClientService reqService, LoginEntityDaoHelper loLoginEntityDaoHelper, CustomerSession customerSession, RegisterPushNorification registerPushnotification) {
        m_reqService = reqService;
        m_loLoginEntityDaoHelper = loLoginEntityDaoHelper;
        m_customerSession = customerSession;
        m_registerPushnotification = registerPushnotification;
    }


    public IFuture<RegistrationPbOuterClass.RegistrationPb, Exception> startRegistration(Context context, RegistrationPbOuterClass.RegistrationPb request) {
        AmazaarApplication.getLoadingDialog().show();
        CustomerRegistrationCF cf = new CustomerRegistrationCF(context, request, m_reqService, m_loLoginEntityDaoHelper, m_customerSession, m_registerPushnotification);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        AmazaarApplication.getLoadingDialog().dismiss();
        return cf.getFutureResult();

    }
}
