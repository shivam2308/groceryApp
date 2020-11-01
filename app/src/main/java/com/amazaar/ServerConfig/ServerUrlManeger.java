package com.amazaar.ServerConfig;

public class ServerUrlManeger {

    private String BASE_URL = "https://developmentserver-4.herokuapp.com/";

    public String getServerUrl(UrlPathProvider.UrlPathEnum data) {
        return BASE_URL + UrlPathProvider.getPath(data);
    }

}
