package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.ItemClientService;
import com.amazaar.CommonCode.AToast;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.prod.basic.common.async.AControlFlow;

import java.util.concurrent.ExecutionException;

public class UpdateItemCF extends AControlFlow<UpdateItemCF.States, ItemPbOuterClass.ItemPb, Exception> {
    private ItemClientService m_itemService;
    private ItemPbOuterClass.ItemPb m_req;
    public UpdateItemCF(ItemPbOuterClass.ItemPb req, ItemClientService itemService) {
        super(States.UPDATE_ITEM, States.DONE);
        m_itemService = itemService;
        m_req = req;
        addStateHandler(States.UPDATE_ITEM, new UpdateItemPbHandler());
    }
    enum States {
        UPDATE_ITEM,
        DONE,
    }
    private class UpdateItemPbHandler implements StateHandler<States>{
        private ItemPbOuterClass.ItemPb m_future;
        @Override
        public void registerCalls(){
            try{
                m_future = m_itemService.update(m_req);
            }
            catch (ExecutionException e) {
                e.printStackTrace();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public States handleState(){
            AToast.itemUpdated();
            AmazaarApplication.getFragmentManager().popBackStack();
            return States.DONE;
        }
    }
}
