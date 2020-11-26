package com.amazaar.ListModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.amazaar.Interfaces.IListModel;
import com.amazaar.Protobuff.ItemPbOuterClass;

import javax.inject.Inject;

public class ProductListModel implements Parcelable, IListModel<ItemPbOuterClass.ItemPb> {

    private int productImage;

    public static final Creator<ProductListModel> CREATOR = new Creator<ProductListModel>() {
        @Override
        public ProductListModel createFromParcel(Parcel in) {
            return new ProductListModel(in);
        }

        @Override
        public ProductListModel[] newArray(int size) {
            return new ProductListModel[size];
        }
    };
    private ItemPbOuterClass.ItemPb m_itemPb;

    @Inject
    public ProductListModel(Parcel in) {
        productImage = in.readInt();
    }
    @Inject
    public ProductListModel() {
    }

    public static Creator<ProductListModel> getCREATOR() {
        return CREATOR;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(productImage);
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    @Override
    public ItemPbOuterClass.ItemPb getPbModel() {
        return m_itemPb;
    }

    @Override
    public void setPbModel(ItemPbOuterClass.ItemPb pb) {
        m_itemPb = pb;
    }
}
