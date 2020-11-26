package com.amazaar.EnumFormatter;

import com.amazaar.Interfaces.IEnumFormatter;
import com.amazaar.Protobuff.GenderPb;

import javax.inject.Inject;

public class GenderEnumFormatter implements IEnumFormatter<GenderPb.GenderEnum> {

    @Inject
    public GenderEnumFormatter() {

    }

    @Override
    public String format(GenderPb.GenderEnum data) {
        switch (data) {
            case MALE:
                return "Male";
            case FEMALE:
                return "Female";
            case UNKNOWN_GENDER:
            default:
                return "";
        }
    }

    @Override
    public GenderPb.GenderEnum getEnum(String data) {
        switch (data) {
            case "Male":
                return GenderPb.GenderEnum.MALE;
            case "Female":
                return GenderPb.GenderEnum.FEMALE;
            case "":
                return GenderPb.GenderEnum.UNKNOWN_GENDER;
            default:
                return GenderPb.GenderEnum.UNKNOWN_GENDER;
        }
    }
}
