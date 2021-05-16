package com.amazaar.EnumFormatter;

import com.amazaar.Interfaces.IEnumFormatter;
import com.amazaar.Protobuff.ItemPbOuterClass;

import javax.inject.Inject;

public class ItemQuantityTypeEnumFormatter implements IEnumFormatter<ItemPbOuterClass.ItemQuantityTypeEnum> {
    @Inject
    public ItemQuantityTypeEnumFormatter() {

    }

    @Override
    public String format(ItemPbOuterClass.ItemQuantityTypeEnum data) {
        switch (data) {
            case KILO_GRAMS:
                return "Kg";
            case GRAMS:
                return "g";
            case PIECE:
                return "Piece";
            case LITRE:
                return "ltr";
            case UNKNOWN_ITEM_QUANTITY_TYPE:
                return "";
            default:
                return "";
        }
    }

    @Override
    public ItemPbOuterClass.ItemQuantityTypeEnum getEnum(String data) {
        switch (data) {
            case "Kg":
                return ItemPbOuterClass.ItemQuantityTypeEnum.KILO_GRAMS;
            case "g":
                return ItemPbOuterClass.ItemQuantityTypeEnum.GRAMS;
            case "Piece":
                return ItemPbOuterClass.ItemQuantityTypeEnum.PIECE;
            case "ltr":
                return ItemPbOuterClass.ItemQuantityTypeEnum.LITRE;
            case "":
                return ItemPbOuterClass.ItemQuantityTypeEnum.UNKNOWN_ITEM_QUANTITY_TYPE;
            default:
                return ItemPbOuterClass.ItemQuantityTypeEnum.UNRECOGNIZED;
        }
    }
}
