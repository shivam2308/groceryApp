package com.amazaar.ServerConfig;

public class UrlPathProvider {

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
                return "pushNotification";
            case BUY:
                return "buy";
            case PAYMENT:
                return "payment";
            case CREATE_BUY:
                return "createBuy";
            case ORDERED_LIST:
                return "orderList";
            case CONFIRM_ORDER:
                return "confirmOrder";
            case CREATE_IMAGE:
                return "createImage";
            case ASSIGN_DELIVERY_MAN:
                return "assignDeliveryMan";
            case CLOSE_AND_OUT_FOR_DELIVERY:
                return "closeAndOutForDelivery";
            default:
                throw new IllegalStateException("Unexpected value: " + data);
        }
    }

    public enum UrlPathEnum {
        UNKNOWN_URL_PATH,
        REGISTRATION_CUSTOMER,
        CUSTOMER,
        LOGIN,
        IMAGE,
        PUSH_NOTIFICATION,
        ITEM,
        BUY,
        PAYMENT,
        CREATE_BUY,
        ORDERED_LIST,
        CONFIRM_ORDER,
        CREATE_IMAGE,
        ASSIGN_DELIVERY_MAN,
        CLOSE_AND_OUT_FOR_DELIVERY
    }
}
