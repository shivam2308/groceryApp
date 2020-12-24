package com.amazaar.ControlFlow;


import android.content.Context;
import android.content.Intent;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.ClientServices.RegistrationClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.LocalDatabase.Enity.LoginEntity;
import com.amazaar.Protobuff.RegistrationPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.async.AControlFlow;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CustomerRegistrationCF extends AControlFlow<CustomerRegistrationCF.States, RegistrationPbOuterClass.RegistrationPb, Exception> {

    private Context m_comtext;
    private RegistrationClientService m_regService;
    private RegistrationPbOuterClass.RegistrationPb m_req;
    private LoginEntityDaoHelper m_loLoginEntityDaoHelper;
    private CustomerSession m_customerSession;
    private RegisterPushNorification m_registerPushNotification;

    public CustomerRegistrationCF(Context comtext, RegistrationPbOuterClass.RegistrationPb req, RegistrationClientService regService, LoginEntityDaoHelper loLoginEntityDaoHelper, CustomerSession customerSession, RegisterPushNorification registerPushNotification) {
        super(States.GET_REGISTRATION, States.DONE);
        m_comtext = comtext;
        m_regService = regService;
        m_req = req;
        m_loLoginEntityDaoHelper = loLoginEntityDaoHelper;
        m_customerSession = customerSession;
        m_registerPushNotification = registerPushNotification;
        addStateHandler(States.GET_REGISTRATION, new GetRagistrationPbHandler());
        addStateHandler(States.STORE_LOGIN_INTO_DB, new StoreIntoLocalDBHandler());
        addStateHandler(States.STORE_LOGIN_INTO_DB, new StoreIntoLocalDBHandler());
        addStateHandler(States.SET_SESSION, new SessionStoreHandler());
        addStateHandler(States.CREATE_PUSH_NOTIFICATION, new CreatePushNotificationHandler());
    }

    enum States {
        GET_REGISTRATION,
        STORE_LOGIN_INTO_DB,
        SET_SESSION,
        CREATE_PUSH_NOTIFICATION,
        DONE,
    }

    private class GetRagistrationPbHandler implements StateHandler<States> {
        private RegistrationPbOuterClass.RegistrationPb m_future;

        @Override
        public void registerCalls() {
            try {
                m_future = m_regService.create(m_req);
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public States handleState() {
            if (m_future != null) {
                m_req = m_future;
                getAsyncCallback().set(0, m_future);
                return States.STORE_LOGIN_INTO_DB;
            }
            return States.DONE;
        }
    }

    private class StoreIntoLocalDBHandler implements StateHandler<States> {


        @Override
        public void registerCalls() {

        }

        @Override
        public States handleState() {
            long m_future = 0l;
            LoginEntity login = new LoginEntity();
            login.setmId(1l);
            login.setEid(m_req.getLogin().getDbInfo().getId());
            try {
                m_future = m_loLoginEntityDaoHelper.getDeoEntity().insert(m_loLoginEntityDaoHelper.fromPb(login, m_req.getLogin()));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (m_future > 0) {
                return States.SET_SESSION;
            }
            return States.DONE;
        }
    }

    private class SessionStoreHandler implements StateHandler<States> {


        @Override
        public void registerCalls() {

        }

        @Override
        public States handleState() {
            m_customerSession.saveObject(m_req.getCustomer());
            return States.CREATE_PUSH_NOTIFICATION;
        }
    }

    private class CreatePushNotificationHandler implements StateHandler<States> {


        @Override
        public void registerCalls() {

        }

        @Override
        public States handleState() {
            m_registerPushNotification.registerPushNptificationToken();
            Intent i = new Intent(m_comtext, HomeActivity.class);
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            m_comtext.startActivity(i);
            return States.DONE;
        }
    }
}
