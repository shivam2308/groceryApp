package com.amazaar.ServerConfig;

public class ServerUrlManeger {

    private final String BASE_URL = "http://192.168.0.108:8000/";

    public String getServerUrl(UrlPathProvider.UrlPathEnum data) {
        return BASE_URL + UrlPathProvider.getPath(data);
    }

}
