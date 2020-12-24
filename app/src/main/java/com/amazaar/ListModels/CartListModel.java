package com.amazaar.ListModels;

import com.amazaar.CommonCode.DataModel;
import com.amazaar.DatabaseEnitityHelper.CartEntityDaoHelper;
import com.amazaar.DefaultProviders.CartPbDefaultProvider;
import com.amazaar.Handlers.CartItemHandler;
import com.amazaar.Protobuff.CartPbOuterClass;

import javax.inject.Inject;

public class CartListModel {
    public DataModel<CartPbOuterClass.CartPb, CartPbDefaultProvider> m_onitemChange;
    private int productImages;
    private String productName;
    private int productQuantity;
    private float productPrice;
    private String kg;

    @Inject
    public CartItemHandler m_handler;

    @Inject
    public CartListModel() {
        m_onitemChange = new DataModel<>(new CartPbDefaultProvider());
        m_handler = new CartItemHandler(new CartEntityDaoHelper());
        m_onitemChange.setListener(new DataModel.ChangeListener() {
            @Override
            public void onChange() {
                if(m_onitemChange.getData().getQuantity()==0){
                    m_handler.deleteItemFromCart(getOnitemChange().getData());
                }else {
                    setKg(String.valueOf(m_onitemChange.getData().getQuantity()));
                    setProductQuantity(m_onitemChange.getData().getQuantity());
                    setProductName(m_onitemChange.getData().getItem().getItemName().getFirstName());
                    setProductPrice(m_onitemChange.getData().getQuantity()*m_onitemChange.getData().getItem().getPrice());
                }
            }
        });
    }

    public DataModel<CartPbOuterClass.CartPb, CartPbDefaultProvider> getOnitemChange() {
        return m_onitemChange;
    }


    public int getProductImages() {
        return productImages;
    }

    public void setProductImages(int productImages) {
        this.productImages = productImages;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public float getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(float productPrice) {
        this.productPrice = productPrice;
    }

    public String getKg() {
        return kg;
    }

    public void setKg(String kg) {
        this.kg = kg;
    }


}
