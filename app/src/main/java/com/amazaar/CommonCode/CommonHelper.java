package com.amazaar.CommonCode;

import com.amazaar.Protobuff.ContactDetailPbOuterClass;
import com.prod.basic.common.code.Strings;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

public class CommonHelper {
    @Inject
    public CommonHelper(){

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
}
