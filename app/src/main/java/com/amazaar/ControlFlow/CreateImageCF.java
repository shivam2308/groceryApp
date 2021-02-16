package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.CreateImageClientService;
import com.amazaar.ClientServices.CustomerClientService;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.code.Strings;

import java.util.concurrent.ExecutionException;

public class CreateImageCF extends AControlFlow<CreateImageCF.State, Void, Exception> {

    private final CreateImageClientService m_createImageClientService;
    private final CustomerSession m_customerSession;
    private final CustomerClientService m_customerService;
    private ImagePbOuterClass.ImagePb m_req;

    public CreateImageCF(ImagePbOuterClass.ImagePb req, CreateImageClientService createImageClientService, CustomerSession customerSession, CustomerClientService customerService) {
        super(State.CREATE_IMAGE, State.DONE);
        m_req = req;
        m_createImageClientService = createImageClientService;
        m_customerSession = customerSession;
        m_customerService = customerService;
        addStateHandler(State.CREATE_IMAGE, new CreateImageHandler());
        addStateHandler(State.GET_AND_UPDATE_CUSTOMER_SESSION, new GetAndUpdateCustomerSession());
    }

    enum State {
        CREATE_IMAGE,
        GET_AND_UPDATE_CUSTOMER_SESSION,
        DONE
    }

    private class CreateImageHandler implements StateHandler<State> {
        ImagePbOuterClass.ImagePb m_future;

        @Override
        public void registerCalls() {
            try {
                m_future = m_createImageClientService.create(m_req);
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public State handleState() {
            if (Strings.notEmpty(m_future.getDbInfo().getId())) {
                m_req = m_future;
                if (m_future.getImageType() == ImagePbOuterClass.ImageTypeEnum.PROFILE_IMAGE) {
                    return State.GET_AND_UPDATE_CUSTOMER_SESSION;
                } else {
                    return State.DONE;
                }
            } else {
                return State.DONE;
            }
        }
    }

    private class GetAndUpdateCustomerSession implements StateHandler<State> {
        CustomerPbOuterClass.CustomerPb m_future;

        @Override
        public void registerCalls() {
            try {
                m_future = m_customerService.get(m_req.getId());
            } catch (ExecutionException | InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public State handleState() {
            m_customerSession.clearSession();
            m_customerSession.saveObject(m_future);
            return State.DONE;
        }
    }
}
