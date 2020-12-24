package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.PushNotificationClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.LoginPbOuterClass;
import com.amazaar.Protobuff.PushNotificationPbOuterClass;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.code.Strings;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RegisterPushNotificationCF extends AControlFlow<RegisterPushNotificationCF.States, Void, Exception> {

    private LoginEntityDaoHelper m_loginEnityDaoHelper;
    private LoginPbOuterClass.LoginPb m_login;
    private PushNotificationClientService m_pushNotificationService;

    public RegisterPushNotificationCF(LoginEntityDaoHelper loginEnityDaoHelper,PushNotificationClientService pushNotificationService) {
        super(States.GET_LOGIN, States.DONE);
        m_loginEnityDaoHelper = loginEnityDaoHelper;
        m_pushNotificationService=pushNotificationService;
        addStateHandler(States.GET_LOGIN, new GetLoginHandler());
        addStateHandler(States.CREATE_PUSH_NOTIFICATION, new CreatePushNotificationHandler());
    }

    enum States {
        GET_LOGIN,
        CREATE_PUSH_NOTIFICATION,
        DONE,
    }

    private class GetLoginHandler implements StateHandler<States> {

        @Override
        public void registerCalls() {

        }

        @Override
        public States handleState() {
            try {
                m_login = m_loginEnityDaoHelper.getLoginPbFromInternalStorage(1l);
            } catch (IOException e) {
                return States.DONE;
            }
            return States.CREATE_PUSH_NOTIFICATION;

        }
    }

    private class CreatePushNotificationHandler implements StateHandler<States> {

        @Override
        public void registerCalls() {
            if(Strings.notEmpty(m_login.getDbInfo().getId())) {
                PushNotificationPbOuterClass.PushNotificationPb.Builder builder = PushNotificationPbOuterClass.PushNotificationPb.newBuilder();
                builder.setCustomerRef(m_login.getCustomerRef());
                builder.setToken(AmazaarApplication.getDeviceToken());
                try {
                    m_pushNotificationService.create(builder.build());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }else{
                //nothing
            }

        }

        @Override
        public States handleState() {
            return States.DONE;

        }
    }

}
