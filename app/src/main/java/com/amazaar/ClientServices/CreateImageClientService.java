package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.CommonCode.VoidPb;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class CreateImageClientService extends AClientService<ImagePbOuterClass.ImagePb, VoidPb, VoidPb> {
    @Inject
    public CreateImageClientService() {
        super(ImagePbOuterClass.ImagePb.class, null, UrlPathProvider.UrlPathEnum.CREATE_IMAGE);
    }
}
