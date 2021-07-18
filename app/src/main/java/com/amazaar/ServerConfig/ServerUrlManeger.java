package com.amazaar.ServerConfig;

import com.amazaar.Module.AmazaarApplication;

public class ServerUrlManeger {

    public String getServerUrl(UrlPathProvider.UrlPathEnum data) {
        return getBASE_URL() + UrlPathProvider.getPath(data);
    }

    public String getBASE_URL() {

        switch (AmazaarApplication.getMode()) {
            case TOOL:
                try {
                    throw new Exception("Tool Not be in use");
                } catch (Exception e) {
                    e.printStackTrace();
                }
            case DEVELOPMENT:
               // return "http://amazaardevel-env.us-east-1.elasticbeanstalk.com/";
                return "http://amazaar-development.tech/";
            case PRODUCTION:
                //return "https://www.amazaar.in/";
                return "http://192.168.29.191:8000/";
            default:
                try {
                    throw new Exception("No Url Found Or Mode is NOt defined Correctly");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return "";
    }
}
