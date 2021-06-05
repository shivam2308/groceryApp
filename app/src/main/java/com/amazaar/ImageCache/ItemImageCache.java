package com.amazaar.ImageCache;

import android.graphics.Bitmap;

import com.amazaar.CommonCode.ImageCacheLoader.MemoryCache;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.prod.basic.common.code.Strings;

import java.util.Iterator;
import java.util.Map;

import javax.inject.Inject;

public class ItemImageCache extends MemoryCache<ImagePbOuterClass.ImagePb> {

    @Inject
    public ItemImageCache() {
        super();
    }

    public ImagePbOuterClass.ImagePb isImagePresent(ImagePbOuterClass.ImageRefPb imageRef) {
            Iterator<Map.Entry<ImagePbOuterClass.ImagePb, Bitmap>> iter=getEntrySet();///least recently accessed item will be the first one iterated
            while(iter.hasNext()){
                Map.Entry<ImagePbOuterClass.ImagePb, Bitmap> entry=iter.next();
                if(Strings.areEqual(entry.getKey().getDbInfo().getId(),imageRef.getId())){
                    return entry.getKey();
                }
            }
        return null;
    }
}
