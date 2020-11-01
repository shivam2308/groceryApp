package com.amazaar.ServerConfig;

public class RequestMethodEnumAndFormatter {

    public enum RequestMethodEnum{
        GET,
        POST,
        PUT,
        DELETE,
    }

    public static int getmethod(RequestMethodEnum data){
        switch (data){
            case GET:
                return 0;
            case POST:
                return 1;
            case PUT:
                return 100;
            case DELETE:
                return 101;
            default:
                throw new IllegalStateException("Unexpected value: " + data);
        }
    }
}
