package com.amazaar.ControlFlow;

import android.view.View;

import com.amazaar.ClientServices.LoginClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Dialog.DailogLoading;
import com.amazaar.LocalDatabase.Enity.LoginEntity;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.LoginPbOuterClass;
import com.amazaar.Widget.ProfileSubmitWidget.ProfileSubmitWidget;
import com.prod.basic.common.async.AControlFlow;

import java.io.IOException;
import java.util.concurrent.ExecutionException;

public class CustomerLoginCF extends AControlFlow<CustomerLoginCF.State, Void, Exception> {
    private LoginClientService m_LoginClientService;
    private LoginPbOuterClass.LoginSearchResponsePb Resp;
    private LoginEntityDaoHelper m_loLoginEntityDaoHelper;
    private ProfileSubmitWidget m_profileSubmitWidget;
    private String m_Mobile;
    private DeviceAutoLogin m_DeviceAutoLogin;
    private RegisterPushNorification m_registrationPushotification;

    public CustomerLoginCF(LoginClientService LoginClientService, String Mobile, LoginEntityDaoHelper loginEntityDaoHelper, DeviceAutoLogin deviceAutoLogin, ProfileSubmitWidget profileSubmitWidget, RegisterPushNorification registrationPushotification) {
        super(State.GET_LOGINS, State.DONE);
        m_LoginClientService = LoginClientService;
        m_loLoginEntityDaoHelper = loginEntityDaoHelper;
        m_DeviceAutoLogin = deviceAutoLogin;
        m_profileSubmitWidget = profileSubmitWidget;
        m_Mobile = Mobile;
        m_registrationPushotification = registrationPushotification;
        addStateHandler(State.STORE_LOGIN_IN_LOCAL_DB, new StoreLoginInLocalDbHandler());
        addStateHandler(State.GET_LOGINS, new GetLoginHandler());
        addStateHandler(State.GET_OR_CREATE_PUSHNOTIFICATION, new GetOrCreatePushNotificationHandler());
        addStateHandler(State.PERFORM_AUTO_LOGIN, new PerformAutoLoginHandler());
    }


    enum State {
        GET_LOGINS,
        STORE_LOGIN_IN_LOCAL_DB,
        GET_OR_CREATE_PUSHNOTIFICATION,
        PERFORM_AUTO_LOGIN,
        DONE
    }

    private class GetLoginHandler implements StateHandler<State> {
        private LoginPbOuterClass.LoginSearchResponsePb m_Resp = null;

        @Override
        public void registerCalls() {
            LoginPbOuterClass.LoginSearchRequestPb.Builder builder = LoginPbOuterClass.LoginSearchRequestPb.newBuilder();
            builder.setMobileNo(m_Mobile);
            try {
                m_Resp = m_LoginClientService.search(builder.build());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public State handleState() {
            if (m_Resp.getSummary().getResultCount() > 0) {
                Resp = m_Resp;
                return State.STORE_LOGIN_IN_LOCAL_DB;
            } else {
                m_profileSubmitWidget.setVisibility(View.GONE);
                m_profileSubmitWidget.setVisibility(View.VISIBLE);
                return State.DONE;
            }
        }
    }

    private class StoreLoginInLocalDbHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            long m_future = 0l;
            LoginEntity login = new LoginEntity();
            login.setmId(1l);
            login.setEid(Resp.getResultsList().get(0).getDbInfo().getId());
            try {
                m_future = m_loLoginEntityDaoHelper.getDeoEntity().insert(m_loLoginEntityDaoHelper.fromPb(login, Resp.getResultsList().get(0)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (m_future > 0) {
                return State.GET_OR_CREATE_PUSHNOTIFICATION;
            }
            return State.DONE;
        }
    }

    private class GetOrCreatePushNotificationHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {
            m_registrationPushotification.registerPushNptificationToken("");
        }

        @Override
        public State handleState() {
            return State.PERFORM_AUTO_LOGIN;
        }
    }

    private class PerformAutoLoginHandler implements StateHandler<State> {

        @Override
        public void registerCalls() {

        }

        @Override
        public State handleState() {
            m_DeviceAutoLogin.doLogin(AmazaarApplication.getContext());
            return State.DONE;
        }
    }

}
