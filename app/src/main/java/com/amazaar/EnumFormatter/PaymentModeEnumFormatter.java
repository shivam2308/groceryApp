package com.amazaar.EnumFormatter;

import com.amazaar.Interfaces.IEnumFormatter;
import com.amazaar.Protobuff.PaymentPbOuterClass;

import javax.inject.Inject;

public class PaymentModeEnumFormatter implements IEnumFormatter<PaymentPbOuterClass.PaymentModeEnum> {

    @Inject
    public PaymentModeEnumFormatter() {

    }

    @Override
    public String format(PaymentPbOuterClass.PaymentModeEnum data) {
        switch (data) {

            case UNKNOWN_MODE:
                return "Unknown Mode";
            case GOOGLE_PAY:
                return "Google Pay";
            case CASH_ON_DELIVERY:
                return "Cash on Delivery";
            default:
                return "";
        }
    }

    @Override
    public PaymentPbOuterClass.PaymentModeEnum getEnum(String data) {
        return null;
    }
}
