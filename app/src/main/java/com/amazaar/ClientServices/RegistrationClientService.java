package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.CommonCode.VoidPb;
import com.amazaar.Protobuff.RegistrationPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

//use only create method..
public class RegistrationClientService extends AClientService<RegistrationPbOuterClass.RegistrationPb, VoidPb, VoidPb> {
    @Inject
    public RegistrationClientService() {
        super(RegistrationPbOuterClass.RegistrationPb.class,null, UrlPathProvider.UrlPathEnum.REGISTRATION_CUSTOMER);
    }
}
