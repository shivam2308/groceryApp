package com.amazaar.CommonCode;

public class DefaultImageUrl {
    public static String getImage(ImageShowTypeEnum imageType) {
        switch (imageType) {
            case MALE_PROFILE:
                return "https://firebasestorage.googleapis.com/v0/b/amazaargrocery.appspot.com/o/images%2FDEFAULT_IMAGE%2F1dc3832c-4463-4135-8e7f-4957921a3f4a?alt=media&token=c14268fe-a7d2-4b19-8988-42f6b9322267";
            case FEMALE_PROFILE:
                return "https://firebasestorage.googleapis.com/v0/b/amazaargrocery.appspot.com/o/images%2FDEFAULT_IMAGE%2F78b23cb7-e81e-40b7-b791-b5a11c3d77eb?alt=media&token=28b5d55d-1a49-456a-90d1-b7f7ddd4282c";
            case ITEM:
                return "https://firebasestorage.googleapis.com/v0/b/amazaargrocery.appspot.com/o/images%2FDEFAULT_IMAGE%2Fe175d406-add4-42f8-b358-fd0698f44d86?alt=media&token=10ecdd74-1ea6-4416-901c-80f21b1b5cee";
            default:
                return "";
        }
    }

    public enum ImageShowTypeEnum {
        MALE_PROFILE,
        FEMALE_PROFILE,
        ITEM,
    }


}
