package com.amazaar.CommonCode;


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

    public static String toTitileCaseFormatter(String data) {
        String finalString = "";
        boolean chk = true;
        int len = data.length();
        return com.prod.basic.common.code.Strings.toTitleCase(data.substring(1, len).replace("^", " "));
    }
}
