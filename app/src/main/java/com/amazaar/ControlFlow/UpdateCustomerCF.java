package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.CustomerClientService;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.async.AControlFlow;

import java.util.concurrent.ExecutionException;

public class UpdateCustomerCF extends AControlFlow<UpdateCustomerCF.States, CustomerPbOuterClass.CustomerPb, Exception> {

    private CustomerClientService m_cusService;
    private CustomerPbOuterClass.CustomerPb m_req;
    private CustomerSession m_customerSession;

    public UpdateCustomerCF(CustomerPbOuterClass.CustomerPb req, CustomerClientService cusService, CustomerSession customerSession){
        super(States.UPDATE_CUSTOMER, States.DONE);
        m_cusService = cusService;
        m_req = req;
        m_customerSession = customerSession;
        addStateHandler(States.UPDATE_CUSTOMER, new UpdateCustomerPbHandler());
        addStateHandler(States.UPDATE_SESSION, new UpdateSessionHandler());
    }
    enum States {
        UPDATE_CUSTOMER,
        UPDATE_SESSION,
        DONE,
    }

    private class UpdateCustomerPbHandler implements StateHandler<States> {
        private CustomerPbOuterClass.CustomerPb m_future;
        @Override
        public void registerCalls() {
            try {
                m_future = m_cusService.update(m_req);
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
//                getAsyncCallback().set(0, m_future);
                return States.UPDATE_SESSION;
            }
            return States.DONE;
        }
    }

    private class UpdateSessionHandler implements StateHandler<States> {
        @Override
        public void registerCalls() {

        }

        @Override
        public States handleState() {
            m_customerSession.clearSession();
            m_customerSession.saveObject(m_req);
            return States.DONE;
        }
    }
}
