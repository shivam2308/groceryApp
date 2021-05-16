package com.amazaar.ControlFlow;

import android.content.Context;
import android.widget.ImageView;

import com.amazaar.ClientServices.ImageClientService;
import com.amazaar.CommonCode.CommonHelper;
import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static com.amazaar.Module.AmazaarApplication.getContext;

public class GetImageFromUrl {
    @Inject
    public ImageClientService m_imageService;
    @Inject
    public CommonHelper m_helper;

    @Inject
    public GetImageFromUrl() {
        injectMembers();
    }

    public void setImageFromUrl(Context context, ImagePbOuterClass.ImageRefPb imageRef, ImageView image, DefaultImageUrl.ImageShowTypeEnum imageType) {
        GetImageFromUrlCF cf = new GetImageFromUrlCF(context,image,imageRef, m_imageService,imageType,m_helper);
        cf.addLogObjects(cf);
        cf.startAsyncCall();
        cf.getFutureResult();
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }
}
