package com.amazaar.ControlFlow;


import android.content.Context;
import android.content.Intent;

import com.amazaar.Activity.BaseActivity;
import com.amazaar.Activity.HomeActivity;
import com.amazaar.ClientServices.CustomerClientService;
import com.amazaar.DatabaseEnitityHelper.LoginEntityDaoHelper;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.Protobuff.LoginPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.code.Strings;

import java.io.IOException;
import java.util.concurrent.ExecutionException;


public class DeviceAutoLoginCF extends AControlFlow<DeviceAutoLoginCF.States, Void, Exception> {

    private Context m_context;
    private LoginEntityDaoHelper m_loginEnityDaoHelper;
    private CustomerClientService m_customerClientService;
    private LoginPbOuterClass.LoginPb m_login;
    private CustomerSession m_customerSession;

    protected DeviceAutoLoginCF(Context context, LoginEntityDaoHelper loginEnityDaoHelper, CustomerClientService customerClientService, CustomerSession customerSession) {
        super(States.GET_LOGIN, States.DONE);
        m_context = context;
        m_loginEnityDaoHelper = loginEnityDaoHelper;
        m_customerClientService = customerClientService;
        m_customerSession = customerSession;
        addStateHandler(States.GET_LOGIN, new GetLoginHandler());
        addStateHandler(States.GET_SESSION, new GetSessionHandler());
    }

    enum States {
        GET_LOGIN,
        GET_SESSION,
        DONE
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
                Intent i = new Intent(m_context, BaseActivity.class);
                m_context.startActivity(i);
                return States.DONE;
            }
            if (Strings.notEmpty(m_login.getDbInfo().getId())) {
                return States.GET_SESSION;
            } else {
                return States.DONE;
            }
        }
    }

    private class GetSessionHandler implements StateHandler<States> {
        CustomerPbOuterClass.CustomerPb customerPb;

        @Override
        public void registerCalls() {
            try {
                customerPb = m_customerClientService.get(m_login.getCustomerRef().getId());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public States handleState() {
            if(customerPb!=null) {
                m_customerSession.saveObject(customerPb);
                Intent i = new Intent(m_context, HomeActivity.class);
                m_context.startActivity(i);
                return States.DONE;
            }else{
                return States.DONE;
            }
        }
    }
}
