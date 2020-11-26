package com.amazaar.DefaultProviders;

import com.amazaar.Interfaces.IDefaultBuilderPbProvider;
import com.amazaar.Protobuff.ImagePbOuterClass;

import javax.inject.Inject;

public class ImagePbDefaultProvider implements IDefaultBuilderPbProvider<ImagePbOuterClass.ImagePb.Builder> {
    @Inject
    public ImagePbDefaultProvider() {
    }

    @Override
    public ImagePbOuterClass.ImagePb.Builder getDefaultPb() {
        return ImagePbOuterClass.ImagePb.newBuilder();
    }
}
