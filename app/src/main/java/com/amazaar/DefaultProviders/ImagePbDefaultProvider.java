package com.amazaar.DefaultProviders;

import com.amazaar.Interfaces.IDefaultBuilderPbProvider;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.google.protobuf.Message;

import javax.inject.Inject;

public class ImagePbDefaultProvider implements IDefaultBuilderPbProvider<ImagePbOuterClass.ImagePb> {
    @Inject
    public ImagePbDefaultProvider() {
    }

    @Override
    public ImagePbOuterClass.ImagePb getDefaultPb() {
        return ImagePbOuterClass.ImagePb.getDefaultInstance();
    }

    @Override
    public Message.Builder getDefaultBuilderPb() {
        return  ImagePbOuterClass.ImagePb.newBuilder();
    }
}
