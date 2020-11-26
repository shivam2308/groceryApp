package com.amazaar.ControlFlow;

import android.content.Context;
import android.widget.ImageView;

import com.amazaar.ClientServices.ImageClientService;
import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.prod.basic.common.httpCommon.Interfaces.IFuture;

import javax.inject.Inject;

public class GetImageFromUrl {
    @Inject
    public ImageClientService m_imageService;

    @Inject
    public GetImageFromUrl() {
    }

    public IFuture<Void, Exception> setImageFromUrl(Context context, ImagePbOuterClass.ImageRefPb imageRef, ImageView image, DefaultImageUrl.ImageShowTypeEnum imageType) {
        GetImageFromUrlCF cf = new GetImageFromUrlCF(context,image,imageRef, m_imageService,imageType);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        return cf.getFutureResult();
    }
}
