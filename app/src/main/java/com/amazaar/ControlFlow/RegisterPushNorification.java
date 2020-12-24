package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.PushNotificationClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;

import javax.inject.Inject;

public class RegisterPushNorification {

    @Inject
    private LoginEntityDaoHelper m_loginEnityDaoHelper;
    @Inject
    private PushNotificationClientService m_pushNotificationService;

    @Inject
    public RegisterPushNorification(LoginEntityDaoHelper loginEnityDaoHelper, PushNotificationClientService pushNotificationService) {
        m_loginEnityDaoHelper = loginEnityDaoHelper;
        m_pushNotificationService = pushNotificationService;
    }

    public void registerPushNptificationToken() {
        RegisterPushNotificationCF cf = new RegisterPushNotificationCF(m_loginEnityDaoHelper, m_pushNotificationService);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        cf.getFutureResult();
    }
}
