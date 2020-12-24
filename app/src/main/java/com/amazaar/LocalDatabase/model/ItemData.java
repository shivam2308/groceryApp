package com.amazaar.LocalDatabase.model;

import com.amazaar.Interfaces.IData;
import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;

import java.io.UnsupportedEncodingException;

public class ItemData implements IData {
    @SerializedName("raw_data")
    private String mRaw_data;
    @SerializedName("eid")
    private String mEid;

    //TODO Other fields
    @SerializedName("id")
    private Long mId;

    @Override
    public Long getmId() {
        return mId;
    }
    @Override
    public void setmId(Long mId) {
        this.mId = mId;
    }

    public String getEIdDecoded() {
        /******************************************************************************************
         *  Following section(s) encodes result into ISO-8859-1 and then decodes it into UTF-8 using decodeString().
         *  This is necessary for displaying accented characters; previously "é" was showing as "Ã©", etc..
         *  Most likely because of an encoding redundancy from the restroom API, so this may break in the future
         *  if that is changed/fixed.
         ******************************************************************************************/
        String mNameDecoded = mEid;
        if (mNameDecoded != null) {
            mNameDecoded = decodeString(mNameDecoded);
        }
        return mNameDecoded;
    }

    // Needed to create separate variable for InfoViewFragment
    @Override
    public String getEid() {
        return mEid;
    }
    @Override
    public void setEid(String mEid) {
        this.mEid = mEid;
    }
    @Override
    public String getData() {
        return mRaw_data;
    }
    @Override
    public void setData(String mRaw_data) {
        this.mRaw_data = mRaw_data;
    }

    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this, ItemData.class);
    }

    public static ItemData fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, ItemData.class);
    }


    private static String decodeString(String string) {
        try {
            string = new String(string.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return string;
    }

    private static String getStringInBytes(String string) {
        try {
            string = new String(string.getBytes("UTF-8"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            //e.printStackTrace();
        }
        return string;
    }
}
