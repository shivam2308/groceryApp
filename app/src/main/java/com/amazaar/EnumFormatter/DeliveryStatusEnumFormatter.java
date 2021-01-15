package com.amazaar.EnumFormatter;

import com.amazaar.Interfaces.IEnumFormatter;
import com.amazaar.Protobuff.BuyPbOuterClass;

import javax.inject.Inject;

public class DeliveryStatusEnumFormatter implements IEnumFormatter<BuyPbOuterClass.DeliveryStatusEnum> {

    @Inject
    public DeliveryStatusEnumFormatter() {

    }

    @Override
    public String format(BuyPbOuterClass.DeliveryStatusEnum data) {
        switch (data) {

            case UNKNOWN_DELIVERY_STATUS:
                return "Unknown Delivery Status";
            case DELIVERED:
                return "Delivered";
            case PENDING:
                return "Pending";
            case CLOSED:
                return "Closed";
            case OUT_FOR_DELIVERY:
                return "Out For Delivery";
            default:
                return "";
        }
    }

    @Override
    public BuyPbOuterClass.DeliveryStatusEnum getEnum(String data) {
        return null;
    }
}
