package com.amazaar.EnumFormatter;

import com.amazaar.Interfaces.IEnumFormatter;
import com.amazaar.Protobuff.AddressPbOuterClass;
import com.amazaar.Protobuff.ItemPbOuterClass;

import javax.inject.Inject;

public class AvailabilityStatusEnumFormatter implements IEnumFormatter<ItemPbOuterClass.AvailabilityStatusEnum> {
    @Inject
    public AvailabilityStatusEnumFormatter() {

    }

    @Override
    public String format(ItemPbOuterClass.AvailabilityStatusEnum data) {
        switch (data) {
            case AVAILABLE:
                return "Available";
            case NOT_AVAILABLE:
                return "Not Available";
            case UNKNOWN_AVAILABILITY_STATUS:
                return "";
            default:
                return "";
        }
    }

    @Override
    public ItemPbOuterClass.AvailabilityStatusEnum getEnum(String data) {
        switch (data) {
            case "Available":
                return ItemPbOuterClass.AvailabilityStatusEnum.AVAILABLE;
            case "Not Available":
                return ItemPbOuterClass.AvailabilityStatusEnum.NOT_AVAILABLE;
            case "":
                return ItemPbOuterClass.AvailabilityStatusEnum.UNKNOWN_AVAILABILITY_STATUS;
            default:
                return ItemPbOuterClass.AvailabilityStatusEnum.UNRECOGNIZED;
        }
    }
}
