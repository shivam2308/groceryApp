package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.PushNotificationClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.SessionManager.CustomerSession;

import javax.inject.Inject;

public class RegisterPushNorification {

    @Inject
    private LoginEntityDaoHelper m_loginEnityDaoHelper;
    @Inject
    private PushNotificationClientService m_pushNotificationService;
    @Inject
    private CustomerSession m_customerSession;

    @Inject
    public RegisterPushNorification(LoginEntityDaoHelper loginEnityDaoHelper, PushNotificationClientService pushNotificationService, CustomerSession customerSession) {
        m_loginEnityDaoHelper = loginEnityDaoHelper;
        m_pushNotificationService = pushNotificationService;
        m_customerSession = customerSession;
    }

    public void registerPushNptificationToken(String token) {
        RegisterPushNotificationCF cf = new RegisterPushNotificationCF(token, m_loginEnityDaoHelper, m_pushNotificationService, m_customerSession);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        cf.getFutureResult();
    }
}
