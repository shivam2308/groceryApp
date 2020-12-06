package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.Protobuff.PushNotificationPbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class PushNotificationClientService extends AClientService<PushNotificationPbOuterClass.PushNotificationPb, PushNotificationPbOuterClass.PushNotificationSearchRequestPb, PushNotificationPbOuterClass.PushNotificationSearchResponsePb> {

    @Inject
    public PushNotificationClientService() {
        super(PushNotificationPbOuterClass.PushNotificationPb.class, PushNotificationPbOuterClass.PushNotificationSearchResponsePb.class, UrlPathProvider.UrlPathEnum.PUSH_NOTIFICATION);
    }
}
