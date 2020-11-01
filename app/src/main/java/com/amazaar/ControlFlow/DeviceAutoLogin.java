package com.amazaar.ControlFlow;

import android.content.Context;

import com.amazaar.ClientServices.CustomerClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class DeviceAutoLogin {

    @Inject
    private LoginEntityDaoHelper m_loginEnityDaoHelper;
    @Inject
    private CustomerClientService m_customerClientService;
    @Inject
    private CustomerSession m_customerSession;

    @Inject
    public DeviceAutoLogin(LoginEntityDaoHelper loginEnityDaoHelper, CustomerClientService customerClientService, CustomerSession customerSession) {
        m_loginEnityDaoHelper = loginEnityDaoHelper;
        m_customerClientService = customerClientService;
        m_customerSession = customerSession;
    }

    public IFuture<Void, Exception> doLogin(Context context) {
        DeviceAutoLoginCF cf = new DeviceAutoLoginCF(context, m_loginEnityDaoHelper, m_customerClientService, m_customerSession);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();

    }

}
