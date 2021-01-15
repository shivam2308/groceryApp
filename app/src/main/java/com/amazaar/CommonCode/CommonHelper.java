package com.amazaar.CommonCode;

import com.amazaar.Protobuff.ContactDetailPbOuterClass;
import com.amazaar.Protobuff.PaymentPbOuterClass;
import com.prod.basic.common.code.Strings;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CommonHelper {
    @Inject
    public CommonHelper() {

    }

    public static <T> List<T> convertArrayToList(T array[]) {
        List<T> list = new ArrayList<>();
        for (T t : array) {
            list.add(t);
        }
        return list;
    }

    public ContactDetailPbOuterClass.EmailPb getEmailPb(String email) {
        ContactDetailPbOuterClass.EmailPb.Builder emailPb = ContactDetailPbOuterClass.EmailPb.newBuilder();
        if (Strings.notEmpty(email)) {
            String[] emailArray = email.split("@");
            emailPb.setLocalPart(emailArray[0]);
            emailPb.setDomainPart(emailArray[1]);
        }
        return emailPb.build();
    }

    public void googlePayResponseToPaymentConvertor(PaymentPbOuterClass.PaymentPb.Builder builder, String resp) {
        String[] splitData = resp.split("&");
        for (String data : splitData) {
            String[] keyValue = data.split("=");
            switch (keyValue[0]) {
                case "txnId":
                    builder.setTxnId(keyValue[1]);
                    break;
                case "responseCode":
                    builder.setResponseCode(keyValue[1]);
                    break;
                case "Status":
                    builder.setStatus(PaymentPbOuterClass.PaymentStatusEnum.valueOf(keyValue[1]));
                    break;
                case "txnRef":
                    builder.setTxnRef(keyValue[1]);
                    break;
            }
        }
    }
}
