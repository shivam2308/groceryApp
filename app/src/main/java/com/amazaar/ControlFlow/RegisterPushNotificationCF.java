package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.PushNotificationClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.LoginPbOuterClass;
import com.amazaar.Protobuff.PushNotificationPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.google.firebase.iid.FirebaseInstanceId;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.code.Strings;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class RegisterPushNotificationCF extends AControlFlow<RegisterPushNotificationCF.States, Void, Exception> {

    private LoginEntityDaoHelper m_loginEnityDaoHelper;
    private LoginPbOuterClass.LoginPb m_login;
    private PushNotificationClientService m_pushNotificationService;
    private CustomerSession m_customerSession;
    private String m_token;
    private PushNotificationPbOuterClass.PushNotificationSearchResponsePb m_pushNotificationResponse;


    public RegisterPushNotificationCF(String token, LoginEntityDaoHelper loginEnityDaoHelper, PushNotificationClientService pushNotificationService, CustomerSession customerSession) {
        super(States.GET_LOGIN, States.DONE);
        m_token = token;
        m_loginEnityDaoHelper = loginEnityDaoHelper;
        m_pushNotificationService = pushNotificationService;
        m_customerSession = customerSession;
        addStateHandler(States.GET_LOGIN, new GetLoginHandler());
        addStateHandler(States.GET_PUSH_NOTIFICATION, new GetPushNotificationHandler());
        addStateHandler(States.CREATE_PUSH_NOTIFICATION, new CreatePushNotificationHandler());
        addStateHandler(States.UPDATE_PUSH_NOTIFICATION, new UpdatePushNotificationHandler());
    }

    enum States {
        GET_LOGIN,
        GET_PUSH_NOTIFICATION,
        CREATE_PUSH_NOTIFICATION,
        UPDATE_PUSH_NOTIFICATION,
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
                if (Strings.isEmpty(m_login.getDbInfo().getId())) {
                    return States.DONE;
                }
            } catch (IOException e) {
                return States.DONE;
            }
            return States.GET_PUSH_NOTIFICATION;

        }
    }

    private class GetPushNotificationHandler implements StateHandler<States> {
        PushNotificationPbOuterClass.PushNotificationSearchResponsePb response;

        @Override
        public void registerCalls() {
            PushNotificationPbOuterClass.PushNotificationSearchRequestPb.Builder builder = PushNotificationPbOuterClass.PushNotificationSearchRequestPb.newBuilder();
            builder.setCustomerRefId(m_login.getCustomerRef().getId());
            try {
                response = m_pushNotificationService.search(builder.build());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public States handleState() {
            if (response.getSummary().getResultCount() != 0) {
                m_pushNotificationResponse = response;
                return States.UPDATE_PUSH_NOTIFICATION;
            } else {
                return States.CREATE_PUSH_NOTIFICATION;
            }
        }
    }

    private class CreatePushNotificationHandler implements StateHandler<States> {

        @Override
        public void registerCalls() {
            if (Strings.notEmpty(m_login.getDbInfo().getId())) {
                PushNotificationPbOuterClass.PushNotificationPb.Builder builder = PushNotificationPbOuterClass.PushNotificationPb.newBuilder();
                builder.setCustomerRef(m_login.getCustomerRef());
                if (Strings.notEmpty(AmazaarApplication.getDeviceToken())) {
                    builder.setToken(AmazaarApplication.getDeviceToken());
                } else if (Strings.notEmpty(m_token)) {
                    builder.setToken(m_token);
                } else {
                    try {
                        builder.setToken(FirebaseInstanceId.getInstance().getToken("796220644087", "FCM"));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try {
                    m_pushNotificationService.create(builder.build());
                } catch (ExecutionException e) {
                    e.printStackTrace();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                //nothing
            }

        }

        @Override
        public States handleState() {
            return States.DONE;

        }
    }

    private class UpdatePushNotificationHandler implements StateHandler<States> {

        @Override
        public void registerCalls() {
            PushNotificationPbOuterClass.PushNotificationPb.Builder builder = PushNotificationPbOuterClass.PushNotificationPb.newBuilder(m_pushNotificationResponse.getResults(0));
            if (Strings.notEmpty(m_token)) {
                builder.setToken(m_token);
            } else {
                Thread t = new Thread(new Runnable(){
                    @Override
                    public void run() {
                        try {
                           m_token =  FirebaseInstanceId.getInstance().getToken("796220644087", "FCM");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                });
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                builder.setToken(m_token);
            }
            try {
                m_pushNotificationService.update(builder.build());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public States handleState() {
            return States.DONE;

        }
    }

}
