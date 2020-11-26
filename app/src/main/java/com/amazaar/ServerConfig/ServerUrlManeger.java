package com.amazaar.ServerConfig;

public class ServerUrlManeger {

    private String BASE_URL = "http://shivamcc.heliohost.us/";

    public String getServerUrl(UrlPathProvider.UrlPathEnum data) {
        return BASE_URL + UrlPathProvider.getPath(data);
    }

}
