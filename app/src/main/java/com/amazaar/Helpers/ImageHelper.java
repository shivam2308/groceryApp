package com.amazaar.Helpers;

import com.amazaar.Protobuff.ImagePbOuterClass;

import javax.inject.Inject;

public class ImageHelper {

    @Inject
    public ImageHelper() {

    }

    public ImagePbOuterClass.ImageRefPb getImageRefFromImagePb(ImagePbOuterClass.ImagePb imagePb){
        ImagePbOuterClass.ImageRefPb.Builder builder = ImagePbOuterClass.ImageRefPb.newBuilder();
        builder.setId(imagePb.getDbInfo().getId());
        builder.setImageId(imagePb.getId());
        return builder.build();
    }
}
