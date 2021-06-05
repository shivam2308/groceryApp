package com.amazaar.ControlFlow;

import android.widget.ImageView;

import com.amazaar.ClientServices.ImageClientService;
import com.amazaar.CommonCode.ImageCacheLoader.ImageLoader;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.prod.basic.common.async.AControlFlow;

import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class GetImageAndStoreInCacheCF extends AControlFlow<GetImageAndStoreInCacheCF.States, Void, Exception> {

    private ImageClientService m_imageClientService;
    private ImagePbOuterClass.ImageSearchResponsePb m_imageResponse;
    private ImageLoader m_imageLoader;


    @Inject
    public GetImageAndStoreInCacheCF(ImageClientService imageClientService, ImageLoader imageLoader) {
        super(States.SEARCH_IMAGES_PB, States.DONE);
        m_imageClientService = imageClientService;
        m_imageLoader = imageLoader;
        addStateHandler(States.SEARCH_IMAGES_PB, new SearchImagePbHandler());
        addStateHandler(States.STORED_IN_CACHE, new StoredInCacheHandler());
    }

    enum States {
        SEARCH_IMAGES_PB,
        STORED_IN_CACHE,
        DONE
    }

    private class SearchImagePbHandler implements StateHandler<GetImageAndStoreInCacheCF.States> {
        ImagePbOuterClass.ImageSearchResponsePb imageResponse;

        @Override
        public void registerCalls() {
            ImagePbOuterClass.ImageSearchRequestPb.Builder builder = ImagePbOuterClass.ImageSearchRequestPb.newBuilder();
            builder.setImageType(ImagePbOuterClass.ImageTypeEnum.ITEM_IMAGE);
            try {
                imageResponse = m_imageClientService.search(builder.build());
            } catch (ExecutionException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public GetImageAndStoreInCacheCF.States handleState() {
            if (imageResponse.getSummary().getResultCount() > 0) {
                m_imageResponse = imageResponse;
                return States.STORED_IN_CACHE;
            } else {
                return States.DONE;
            }
        }
    }

    private class StoredInCacheHandler implements StateHandler<GetImageAndStoreInCacheCF.States> {
        ImagePbOuterClass.ImageSearchResponsePb imageResponse;

        @Override
        public void registerCalls() {

        }

        @Override
        public GetImageAndStoreInCacheCF.States handleState() {
            for (ImagePbOuterClass.ImagePb imagePb : m_imageResponse.getResultsList()) {
                ImageView image=new ImageView(AmazaarApplication.getContext());
                m_imageLoader.DisplayImage(imagePb,image);
            }
            return States.DONE;
        }
    }
}
