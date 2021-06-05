package com.amazaar.CommonCode;

import com.amazaar.R;

public class DefaultImageUrl {
    public static int getImage(ImageShowTypeEnum imageType) {
        switch (imageType) {
            case MALE_PROFILE:
                return R.drawable.femaledefault;
            case FEMALE_PROFILE:
                return R.drawable.maledefault;
            case ITEM:
                return R.drawable.defaultitem;
            default:
                return 0;
        }
    }

    public enum ImageShowTypeEnum {
        MALE_PROFILE,
        FEMALE_PROFILE,
        ITEM,
    }


}
