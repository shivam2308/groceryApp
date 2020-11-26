package com.amazaar.ClientServices;

import com.amazaar.BaseClientService.AClientService;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.ServerConfig.UrlPathProvider;

import javax.inject.Inject;

public class ImageClientService extends AClientService<ImagePbOuterClass.ImagePb, ImagePbOuterClass.ImageSearchRequestPb, ImagePbOuterClass.ImageSearchResponsePb> {
    @Inject
    public ImageClientService() {
        super(ImagePbOuterClass.ImagePb.class, ImagePbOuterClass.ImageSearchResponsePb.class, UrlPathProvider.UrlPathEnum.IMAGE);
    }
}
