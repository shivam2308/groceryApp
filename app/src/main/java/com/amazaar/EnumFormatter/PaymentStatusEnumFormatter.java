package com.amazaar.EnumFormatter;

import com.amazaar.Interfaces.IEnumFormatter;
import com.amazaar.Protobuff.PaymentPbOuterClass;

import javax.inject.Inject;

public class PaymentStatusEnumFormatter implements IEnumFormatter<PaymentPbOuterClass.PaymentStatusEnum> {

    @Inject
    public PaymentStatusEnumFormatter() {

    }

    @Override
    public String format(PaymentPbOuterClass.PaymentStatusEnum data) {
        switch (data) {

            case UNKNOWN_PAYMENT_STATUS:
               return "Unknown Payment Status";
            case SUCCESS:
                return "Success";
            case FAILURE:
                return "Failed";
            case SUBMITTED:
                return "Submitted";
            default:
                return "";
        }
    }

    @Override
    public PaymentPbOuterClass.PaymentStatusEnum getEnum(String data) {
        return null;
    }
}
