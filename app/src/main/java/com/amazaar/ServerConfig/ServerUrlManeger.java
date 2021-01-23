package com.amazaar.ServerConfig;

public class ServerUrlManeger {

    private final String BASE_URL = "http://192.168.29.191:8000/";

    public String getServerUrl(UrlPathProvider.UrlPathEnum data) {
        return BASE_URL + UrlPathProvider.getPath(data);
    }

}
