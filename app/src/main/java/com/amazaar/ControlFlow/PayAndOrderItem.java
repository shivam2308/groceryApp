package com.amazaar.ControlFlow;

import com.amazaar.ClientServices.CreateBuyClientService;
import com.amazaar.ClientServices.PaymentClientService;
import com.amazaar.CommonCode.CommonHelper;
import com.amazaar.Convertors.RefConvertorHelper;
import com.amazaar.DatabaseEnitityHelper.CartEntityDaoHelper;
import com.amazaar.SessionManager.CustomerSession;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getContext;

public class PayAndOrderItem {

    @Inject
    private PaymentClientService m_paymentClientService;
    @Inject
    private CreateBuyClientService m_createBuyService;
    @Inject
    private CustomerSession m_customerSession;
    @Inject
    private RefConvertorHelper m_refConvertorHelper;
    @Inject
    private CartEntityDaoHelper m_cartEntityDeo;
    @Inject
    private CommonHelper m_helper;

    public PayAndOrderItem(){
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }


    @Inject
    public PayAndOrderItem(PaymentClientService paymentClientService, CreateBuyClientService createBuyService, CustomerSession customerSession, RefConvertorHelper refConvertorHelper, CartEntityDaoHelper cartEntityDeo,CommonHelper helper) {
        m_paymentClientService = paymentClientService;
        m_createBuyService = createBuyService;
        m_customerSession = customerSession;
        m_refConvertorHelper = refConvertorHelper;
        m_cartEntityDeo = cartEntityDeo;
        m_helper=helper;
    }


    public void createBuyItem(String resp) {
        PayAndOrderItemCF cf = new PayAndOrderItemCF(resp,m_paymentClientService, m_createBuyService, m_customerSession, m_refConvertorHelper, m_cartEntityDeo,m_helper);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        cf.getFutureResult();
    }
}
