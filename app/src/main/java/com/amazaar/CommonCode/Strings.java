package com.amazaar.CommonCode;


import android.content.Context;
import android.widget.ImageView;

import com.amazaar.EnumFormatter.StateEnumFormatter;
import com.amazaar.Protobuff.AddressPbOuterClass;
import com.amazaar.Protobuff.ContactDetailPbOuterClass;
import com.amazaar.Protobuff.NamePbOuterClass;
import com.budiyev.android.imageloader.ImageLoader;

import java.util.stream.Stream;

public class Strings {


   /* public static Email.EmailIdPb getEmailIdPbFromStringId(String emailId) {
        String[] email = emailId.split("@");
        Email.EmailIdPb.Builder builder = Email.EmailIdPb.newBuilder();
        builder.setLocalPart(email[0]);
        builder.setDomain(email[1]);
        return builder.build();
    }*/

    public static boolean isEmail(String text) {
        if (text.contains("@")) {
            return true;
        }
        return false;
    }

    public static String getEmail(ContactDetailPbOuterClass.EmailPb emailPb) {
        return emailPb.getLocalPart() + "@" + emailPb.getDomainPart();
    }

    public static String getAddress(AddressPbOuterClass.AddressPb addressPb) {
        StateEnumFormatter m_stateEnumFormatter = new StateEnumFormatter();
        return toTitileCaseFormatter(addressPb.getHomeNo() + " " + addressPb.getStreet() + " " + addressPb.getLandMark() + " " + addressPb.getCity().name() + " " + m_stateEnumFormatter.format(addressPb.getState()) + " " + addressPb.getPincode());
    }

    public static String toTitileCaseFormatter(String data) {
        return titleCaseConversion(data);
    }

    public static String titleCase(NamePbOuterClass.NamePb namePb) {
        return titleCaseConversion(namePb.getFirstName() + " " + namePb.getLastName());
    }

    private static String titleCaseConversion(String inputString) {
        if (Strings.isBlank(inputString)) {
            return "";
        }

        if (Strings.length(inputString) == 1) {
            return inputString.toUpperCase();
        }

        StringBuffer resultPlaceHolder = new StringBuffer(inputString.length());

        Stream.of(inputString.split(" ")).forEach(stringPart ->
        {
            if (stringPart.length() > 1)
                resultPlaceHolder.append(stringPart.substring(0, 1)
                        .toUpperCase())
                        .append(stringPart.substring(1)
                                .toLowerCase());
            else
                resultPlaceHolder.append(stringPart.toUpperCase());

            resultPlaceHolder.append(" ");
        });
        return Strings.trim(resultPlaceHolder.toString());
    }

    private static boolean isBlank(String inputString) {
        return com.prod.basic.common.code.Strings.isEmpty(inputString);
    }

    private static int length(String inputString) {
        return inputString.length();
    }

    private static String trim(String toString) {
        return toString.trim();
    }

}
