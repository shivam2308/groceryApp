package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.LoginClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Widget.ProfileSubmitWidget.ProfileSubmitWidget;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class CustomerLogin {
    @Inject
    public LoginClientService m_LoginClientService;
    @Inject
    public DeviceAutoLogin m_DeviceAutoLogin;
    @Inject
    public LoginEntityDaoHelper m_loginEntityDaoHelper;

    @Inject
    public CustomerLogin(LoginClientService loginClientService, DeviceAutoLogin deviceAutoLogin, LoginEntityDaoHelper loginEntityDaoHelper){
        m_LoginClientService = loginClientService;
        m_DeviceAutoLogin = deviceAutoLogin;
        m_loginEntityDaoHelper = loginEntityDaoHelper;
    }

    public IFuture<Void, Exception> login(String mobile, ProfileSubmitWidget m_profileSubmitWidget){
        CustomerLoginCF cf = new CustomerLoginCF(m_LoginClientService, mobile, m_loginEntityDaoHelper, m_DeviceAutoLogin, m_profileSubmitWidget);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();
    }
}
