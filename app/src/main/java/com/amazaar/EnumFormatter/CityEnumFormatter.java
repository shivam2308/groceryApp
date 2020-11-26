package com.amazaar.EnumFormatter;

import com.amazaar.Interfaces.IEnumFormatter;
import com.amazaar.Protobuff.AddressPbOuterClass;

import javax.inject.Inject;

public class CityEnumFormatter implements IEnumFormatter<AddressPbOuterClass.CityEnum> {
    @Inject
    public CityEnumFormatter() {

    }

    @Override
    public String format(AddressPbOuterClass.CityEnum data) {
        switch (data) {
            case LUCKNOW:
                return "Lucknow";
            case SITAPUR:
                return "Sitapur";
            case UNKNOWN_CITY:
                return "Select City";
            default:
                return "";
        }
    }

    @Override
    public AddressPbOuterClass.CityEnum getEnum(String data) {
        switch (data) {
            case "Lucknow":
                return AddressPbOuterClass.CityEnum.LUCKNOW;
            case "Sitapur":
                return AddressPbOuterClass.CityEnum.SITAPUR;
            case "Select City":
                return AddressPbOuterClass.CityEnum.UNKNOWN_CITY;
            default:
                return AddressPbOuterClass.CityEnum.UNRECOGNIZED;
        }
    }
}
