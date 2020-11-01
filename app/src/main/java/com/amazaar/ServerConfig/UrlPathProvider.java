package com.amazaar.ServerConfig;

public class UrlPathProvider {

    public enum UrlPathEnum {
        UNKNOWN_URL_PATH,
        REGISTRATION_CUSTOMER,
        CUSTOMER,
        LOGIN,
        WORKER_TYPE,
        PUSH_NOTIFICATION,
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
                return "loginMain";
            case WORKER_TYPE:
                return "workerTypeMain";
            case PUSH_NOTIFICATION:
                return "pushNotificationMain";
            default:
                throw new IllegalStateException("Unexpected value: " + data);
        }
    }
}
