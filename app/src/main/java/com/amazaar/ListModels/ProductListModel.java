package com.amazaar.ListModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.amazaar.Interfaces.IListModel;
import com.amazaar.Protobuff.ItemPbOuterClass;

import javax.inject.Inject;

public class ProductListModel implements Parcelable, IListModel<ItemPbOuterClass.ItemPb> {

    private int productImage;
    private String productName;
    private String productPrice;
    private String kG;
    private int totalKg;
    private boolean isLike=false;

    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    @Inject
    public ProductListModel(Parcel in) {
        productName = in.readString();
        productPrice = in.readString();
        productImage = in.readInt();
        kG = in.readString();
        totalKg = in.readInt();

    }

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
        dest.writeString(productName);
        dest.writeString(productPrice);
        dest.writeInt(productImage);
        dest.writeString(kG);
        dest.writeInt(totalKg);
    }

    public int getTotalKg() {
        return totalKg;
    }
    public void setTotalKg(int totalKg) {
        this.totalKg = totalKg;
    }

    public String getkG() {
        return kG;
    }
    public void setkG(String kG) {
        this.kG = kG;
    }

    public int getProductImage() {
        return productImage;
    }
    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProductName() {
        return productName;
    }
    public void setProductName(String productName) { this.productName = productName; }

    public String getProductPrice() {
        return productPrice;
    }
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
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
