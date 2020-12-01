package com.amazaar.ListModels;

import android.os.Parcel;
import android.os.Parcelable;

import com.amazaar.CommonCode.DataModel;
import com.amazaar.DefaultProviders.ItemPbDefultProvider;
import com.amazaar.Interfaces.IListModel;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.prod.basic.common.code.Strings;

import javax.inject.Inject;

public class ProductListModel implements Parcelable, IListModel<ItemPbOuterClass.ItemPb> {

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

    public DataModel<ItemPbOuterClass.ItemPb, ItemPbDefultProvider> m_onitemChange;
    private ItemPbOuterClass.ItemTypeEnum m_itemTypeEnum ;
    private String productName;
    private String productPrice;
    private int productImage;
    private String kG;
    private int totalKg;
    private boolean isLike = false;

    public ProductListModel(Parcel in) {
        productImage = in.readInt();

    }

    @Inject
    public ProductListModel() {
        m_onitemChange= new DataModel<>(new ItemPbDefultProvider());
        m_onitemChange.setListener(new DataModel.ChangeListener() {
            @Override
            public void onChange() {
                productName = m_onitemChange.getData().getItemName().getFirstName();
                productPrice = String.valueOf(m_onitemChange.getData().getPrice());
            }
        });
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


    public boolean isLike() {
        return isLike;
    }

    public void setLike(boolean like) {
        isLike = like;
    }

    public ItemPbOuterClass.ItemTypeEnum getItemType() {
        return m_itemTypeEnum;
    }

    public void setItemType(ItemPbOuterClass.ItemTypeEnum itemTypeEnum) {
        m_itemTypeEnum = itemTypeEnum;
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


    public String getProductPrice() {
        return productPrice;
    }


    @Override
    public ItemPbOuterClass.ItemPb getPbModel() {
        if(Strings.notEmpty(m_onitemChange.getData().getDbInfo().getId())) {
            return m_onitemChange.getData();
        }else{
            return null;
        }
    }

    @Override
    public void setPbModel(ItemPbOuterClass.ItemPb pb) {
        m_onitemChange.setVar(pb);
    }
}
