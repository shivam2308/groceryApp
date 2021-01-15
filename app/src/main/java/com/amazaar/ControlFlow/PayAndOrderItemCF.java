package com.amazaar.ControlFlow;

import android.util.Log;

import com.amazaar.ClientServices.CreateBuyClientService;
import com.amazaar.ClientServices.PaymentClientService;
import com.amazaar.CommonCode.AToast;
import com.amazaar.CommonCode.CommonHelper;
import com.amazaar.Convertors.RefConvertorHelper;
import com.amazaar.DatabaseEnitityHelper.CartEntityDaoHelper;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.Protobuff.CartPbOuterClass;
import com.amazaar.Protobuff.PaymentPbOuterClass;
import com.amazaar.SessionManager.CustomerSession;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.code.Strings;

import java.util.concurrent.ExecutionException;

public class PayAndOrderItemCF extends AControlFlow<PayAndOrderItemCF.States, Void, Exception> {

    private PaymentClientService m_paymentClientService;
    private CreateBuyClientService m_createBuyService;
    private CustomerSession m_customerSession;
    private RefConvertorHelper m_refConvertorHelper;
    private PaymentPbOuterClass.PaymentPb m_payment;
    private CartEntityDaoHelper m_cartEntityDeo;
    private String m_googlePayResp;
    private CommonHelper m_helper;
    private PaymentPbOuterClass.PaymentPb.Builder m_paymentPb = PaymentPbOuterClass.PaymentPb.newBuilder();
    private BuyPbOuterClass.CreateBuyRequestPb.Builder m_createBuyRequestPb = BuyPbOuterClass.CreateBuyRequestPb.newBuilder();


    public PayAndOrderItemCF(String googlePayResp, PaymentClientService paymentClientService, CreateBuyClientService createBuyService, CustomerSession customerSession, RefConvertorHelper refConvertorHelper, CartEntityDaoHelper cartEntityDeo, CommonHelper helper) {
        super(States.GET_PAYMENT, States.DONE);
        m_googlePayResp = googlePayResp;
        m_paymentClientService = paymentClientService;
        m_createBuyService = createBuyService;
        m_customerSession = customerSession;
        m_refConvertorHelper = refConvertorHelper;
        m_cartEntityDeo = cartEntityDeo;
        m_helper = helper;
        addStateHandler(States.GET_PAYMENT, new GetPaymentPbHandler());
        addStateHandler(States.CREATE_PAYMENT, new CreatePaymentPbHandler());
        addStateHandler(States.GET_ITEM_LIST, new GetCartListPbHandler());
        addStateHandler(States.CREATE_BUY_ITEM, new CreateBuyItemPbHandler());
        addStateHandler(States.DELETE_ALL_ITEMS_CART, new DeleteItemFromCartHandler());
    }

    enum States {
        GET_PAYMENT,
        CREATE_PAYMENT,
        GET_ITEM_LIST,
        CREATE_BUY_ITEM,
        DELETE_ALL_ITEMS_CART,
        DONE
    }

    private class GetPaymentPbHandler implements StateHandler<States> {

        @Override
        public void registerCalls() {

        }

        @Override
        public States handleState() {
            if (Strings.notEmpty(m_googlePayResp)) {
                m_helper.googlePayResponseToPaymentConvertor(m_paymentPb, m_googlePayResp);
                m_paymentPb.setMode(PaymentPbOuterClass.PaymentModeEnum.GOOGLE_PAY);
                if(m_paymentPb.getStatus()== PaymentPbOuterClass.PaymentStatusEnum.SUCCESS){
                    return States.GET_ITEM_LIST;
                }
                else{
                    AToast.userPaymentFailed();
                    return States.DONE;
                }
            } else {
                m_paymentPb.setMode(PaymentPbOuterClass.PaymentModeEnum.CASH_ON_DELIVERY);
                return States.GET_ITEM_LIST;
            }
        }
    }

    private class CreatePaymentPbHandler implements StateHandler<States> {
        PaymentPbOuterClass.PaymentPb payment;

        @Override
        public void registerCalls() {
            m_paymentPb.setCustomerRef(m_refConvertorHelper.getCustomerRef(m_customerSession.getSession()));
            m_paymentPb.getTimeBuilder().setTimezone(m_customerSession.getSession().getDbInfo().getLocale().getDefaultTimeZone());
            try {
                payment = m_paymentClientService.create(m_paymentPb.build());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public States handleState() {
            if (Strings.notEmpty(payment.getDbInfo().getId())) {
                m_payment = payment;
                m_createBuyRequestPb.setPaymentRefId(payment.getDbInfo().getId());
                return States.CREATE_BUY_ITEM;
            } else {
                return States.DONE;
            }

        }
    }

    private class GetCartListPbHandler implements StateHandler<States> {
        @Override
        public void registerCalls() {


        }

        @Override
        public States handleState() {
            float total = 0;
            m_createBuyRequestPb.setCustomerId(m_customerSession.getSession().getDbInfo().getId());
            for (CartPbOuterClass.CartPb cartitem : m_cartEntityDeo.getCartListPb().getCartItemList()) {
                if (cartitem.getQuantity() > 0) {
                    m_createBuyRequestPb.addItemIdAndQuantity(com.amazaar.CommonCode.Strings.getJoinString(cartitem.getItem().getDbInfo().getId(), String.valueOf(cartitem.getQuantity()), "@"));
                    total = total + cartitem.getQuantity() * cartitem.getItem().getPrice();
                } else {
                    m_cartEntityDeo.getDeoEntity().deleteByKey(cartitem.getItemKey());
                }
            }
            m_paymentPb.setAmount(total);
            if (m_createBuyRequestPb.getItemIdAndQuantityList().size() > 0) {
                return States.CREATE_PAYMENT;
            } else {
                return States.DONE;
            }
        }
    }

    private class CreateBuyItemPbHandler implements StateHandler<States> {
        BuyPbOuterClass.CreateBuyRequestPb resp;

        @Override
        public void registerCalls() {
            try {
                resp = m_createBuyService.create(m_createBuyRequestPb.build());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        @Override
        public States handleState() {
            if (resp.getIsOrderPlaced() == BuyPbOuterClass.IsOrderedPlacedEnum.ORDER_SUCCESS) {
                Log.e("done", "Success");

                return States.DELETE_ALL_ITEMS_CART;
            } else {
                Log.e("done", "Failed");
            }
            return States.DONE;
        }
    }

    private class DeleteItemFromCartHandler implements StateHandler<States> {

        @Override
        public void registerCalls() {

        }

        @Override
        public States handleState() {
            m_cartEntityDeo.getDeoEntity().deleteAll();
            int fm = AmazaarApplication.getFragmentManager().getBackStackEntryCount();
            for (int i = 0; i < fm; ++i) {
                AmazaarApplication.getFragmentManager().popBackStack();
            }
            return States.DONE;
        }
    }

}
