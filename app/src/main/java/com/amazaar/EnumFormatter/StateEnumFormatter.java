package com.amazaar.EnumFormatter;

import com.amazaar.Interfaces.IEnumFormatter;
import com.amazaar.Protobuff.AddressPbOuterClass;

import javax.inject.Inject;

public class StateEnumFormatter implements IEnumFormatter<AddressPbOuterClass.StateEnum> {

    @Inject
    public StateEnumFormatter() {

    }

    @Override
    public String format(AddressPbOuterClass.StateEnum data) {
        switch (data) {
            case UTTAR_PRADESH:
                return "Uttar Pradesh";
            case UNKNOWN_STATE:
                return "Select State";
            default:
                return "";
        }
    }

    @Override
    public AddressPbOuterClass.StateEnum getEnum(String data) {
        switch (data) {
            case "Uttar Pradesh":
                return AddressPbOuterClass.StateEnum.UTTAR_PRADESH;
            case "Select State":
                return AddressPbOuterClass.StateEnum.UNKNOWN_STATE;
            default:
                return AddressPbOuterClass.StateEnum.UNRECOGNIZED;
        }
    }
}
