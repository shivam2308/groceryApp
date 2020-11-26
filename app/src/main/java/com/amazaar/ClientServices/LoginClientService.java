package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.Protobuff.LoginPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class LoginClientService extends AClientService<LoginPbOuterClass.LoginPb, LoginPbOuterClass.LoginSearchRequestPb, LoginPbOuterClass.LoginSearchResponsePb> {
    @Inject
    public LoginClientService() {
        super(LoginPbOuterClass.LoginPb.class, LoginPbOuterClass.LoginSearchResponsePb.class, UrlPathProvider.UrlPathEnum.LOGIN);
    }
}
