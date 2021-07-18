package com.amazaar.Widget.EditProductDetailWidget;

import android.widget.Switch;

import com.amazaar.CommonCode.DataModel;
import com.amazaar.ControlFlow.UpdateItem;
import com.amazaar.DefaultProviders.ItemPbDefultProvider;
import com.amazaar.EnumFormatter.ItemQuantityTypeEnumFormatter;
import com.amazaar.Fragments.EditProductDetailFragment;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.prod.basic.common.collect.Lists;

import java.util.List;

import javax.inject.Inject;

public class EditProductDetailView {
    @Inject
    private EditProductDetailFragment m_mainFragment;
    private DataModel<ItemPbOuterClass.ItemPb, ItemPbDefultProvider> m_itemPbModel;
    @Inject
    public ItemQuantityTypeEnumFormatter m_itemQuantityTypeEnumFormatter;
    @Inject
    public UpdateItem m_updateItem;

    @Inject
    public EditProductDetailView(ItemQuantityTypeEnumFormatter itemQuantityTypeEnumFormatter, UpdateItem updateItem){
        m_itemPbModel = new DataModel<>(new ItemPbDefultProvider());
        m_itemQuantityTypeEnumFormatter = itemQuantityTypeEnumFormatter;
        m_updateItem = updateItem;
    }


    public DataModel<ItemPbOuterClass.ItemPb, ItemPbDefultProvider> getItemPbMOdel(){
        return m_itemPbModel;
    }

    public void setMainFragment(EditProductDetailFragment editProductDetailFragment) {
         m_mainFragment = editProductDetailFragment;
    }

    public List<String> getQuantityTypeList() {
        List<String> quantityTypeList = Lists.newArrayList();
        //quantityTypeList.add("Select Quantity Type");
        for (ItemPbOuterClass.ItemQuantityTypeEnum quantityType :
                ItemPbOuterClass.ItemQuantityTypeEnum.values()) {
            if (quantityType != ItemPbOuterClass.ItemQuantityTypeEnum.UNRECOGNIZED) {
                quantityTypeList.add(m_itemQuantityTypeEnumFormatter.format(quantityType));
            }
        }
        return quantityTypeList;
    }
    public int getQuantityTypePosition(ItemPbOuterClass.ItemQuantityTypeEnum quantityType){
        int counter = 0;
        for (ItemPbOuterClass.ItemQuantityTypeEnum quantityTypeEnum :
                ItemPbOuterClass.ItemQuantityTypeEnum.values()) {
            if (quantityTypeEnum != ItemPbOuterClass.ItemQuantityTypeEnum.UNRECOGNIZED && quantityTypeEnum == quantityType) {
               break;
            }
            else{
                counter++;
            }
        }
        return counter;
    }
    public ItemPbOuterClass.AvailabilityStatusEnum getAvailabilityStatus(Switch switchbutton){
        if(switchbutton.isChecked()){
            return ItemPbOuterClass.AvailabilityStatusEnum.AVAILABLE;
        }
        else{
            return ItemPbOuterClass.AvailabilityStatusEnum.NOT_AVAILABLE;
        }
    }

    public void updateCurrentItem(String productName, String productPrice, String productQuantity, ItemPbOuterClass.AvailabilityStatusEnum availabilityStatus, String m_quantity_type) {
        ItemPbOuterClass.ItemPb.Builder builder = ItemPbOuterClass.ItemPb.newBuilder(getItemPbMOdel().getData());
        builder.getItemNameBuilder().setFirstName(productName);
        builder.setPrice(Float.valueOf(productPrice));
        builder.setQuantity(productQuantity);
        builder.setAvailabilityStatus(availabilityStatus);
        builder.setItemQuantityType(m_itemQuantityTypeEnumFormatter.getEnum(m_quantity_type));
        m_updateItem.updateItem(builder.build());
    }
}
