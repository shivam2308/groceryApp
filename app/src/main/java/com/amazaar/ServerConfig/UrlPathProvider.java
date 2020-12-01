package com.amazaar.ServerConfig;

public class UrlPathProvider {

    public enum UrlPathEnum {
        UNKNOWN_URL_PATH,
        REGISTRATION_CUSTOMER,
        CUSTOMER,
        LOGIN,
        IMAGE,
        PUSH_NOTIFICATION,
        ITEM,
    }

    public static String getPath(UrlPathEnum data) {
        switch (data) {

            case UNKNOWN_URL_PATH:
                return "";
            case REGISTRATION_CUSTOMER:
                return "registration";
            case CUSTOMER:
                return "customer";
            case LOGIN:
                return "login";
            case IMAGE:
                return "image";
            case ITEM:
                return "item";
            case PUSH_NOTIFICATION:
                return "pushNotificationMain";
            default:
                throw new IllegalStateException("Unexpected value: " + data);
        }
    }
}
