package com.amazaar.ControlFlow;

import android.content.Context;
import android.widget.ImageView;

import com.amazaar.ClientServices.ImageClientService;
import com.amazaar.CommonCode.CommonHelper;
import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.Protobuff.ImagePbOuterClass;

import javax.inject.Inject;

public class GetImageFromUrl {
    @Inject
    public ImageClientService m_imageService;
    @Inject
    public CommonHelper m_helper;

    @Inject
    public GetImageFromUrl() {
    }

    public void setImageFromUrl(Context context, ImagePbOuterClass.ImageRefPb imageRef, ImageView image, DefaultImageUrl.ImageShowTypeEnum imageType) {
        GetImageFromUrlCF cf = new GetImageFromUrlCF(context,image,imageRef, m_imageService,imageType,m_helper);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        cf.getFutureResult();
    }
}
