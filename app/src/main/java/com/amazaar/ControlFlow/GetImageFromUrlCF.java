package com.amazaar.ControlFlow;

import android.content.Context;
import android.widget.ImageView;

import com.amazaar.ClientServices.ImageClientService;
import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.budiyev.android.imageloader.ImageLoader;
import com.prod.basic.common.async.AControlFlow;
import com.prod.basic.common.code.Strings;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class GetImageFromUrlCF extends AControlFlow<GetImageFromUrlCF.States, Void, Exception> {

    private Context m_context;
    private ImageView m_image;
    private DefaultImageUrl.ImageShowTypeEnum m_imageType;
    private ImagePbOuterClass.ImagePb m_imagePb;
    private ImagePbOuterClass.ImageRefPb m_imageRef;
    private ImageClientService m_imageService;


    @Inject
    public GetImageFromUrlCF(Context context, ImageView imageView, ImagePbOuterClass.ImageRefPb imageRefPb, ImageClientService imageService, DefaultImageUrl.ImageShowTypeEnum imageType) {
        super(States.GET_IMAGE_PB, States.DONE);
        m_context = context;
        m_image = imageView;
        m_imageRef = imageRefPb;
        m_imageService = imageService;
        m_imageType = imageType;
        addStateHandler(States.GET_IMAGE_PB, new GetImageHandler());
        addStateHandler(States.SET_IMAGE, new SetImageHandler());
    }

    enum States {
        GET_IMAGE_PB,
        SET_IMAGE,
        DONE
    }

    private class GetImageHandler implements StateHandler<States> {
        ImagePbOuterClass.ImagePb m_imagepb;

        @Override
        public void registerCalls() {
            try {
                if(Strings.notEmpty(m_imageRef.getId())) {
                    m_imagepb = m_imageService.get(m_imageRef.getId());
                }else{
                    ImageLoader.with(m_context).from(DefaultImageUrl.getImage(m_imageType)).load(m_image);
                }
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public States handleState() {
            if (Strings.notEmpty(m_imagePb.getId())) {
                m_imagePb = m_imagepb;
                return States.SET_IMAGE;
            } else {
                return States.DONE;
            }
        }
    }

    private class SetImageHandler implements StateHandler<States> {
        @Override
        public void registerCalls() {

        }

        @Override
        public States handleState() {
            ImageLoader.with(m_context).from(m_imagePb.getUrl()).load(m_image);
            return States.DONE;
        }
    }
}
