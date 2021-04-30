package com.amazaar.Widget.ProductListWidget;

import android.util.Log;
import android.widget.ImageView;

import com.amazaar.Adapters.ProductListAdapter;
import com.amazaar.ClientServices.ItemClientService;
import com.amazaar.Convertors.ItemPbToProductListModelConvertor;
import com.amazaar.Fragments.ProductDetailsFragment;
import com.amazaar.Fragments.ProductListFragment;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Protobuff.ItemPbOuterClass;

import java.util.List;
import java.util.concurrent.ExecutionException;

import javax.inject.Inject;

public class ProductListView {

    @Inject
    public ItemPbToProductListModelConvertor m_listConvertor;
    @Inject
    public VariableValueChange<ItemPbOuterClass.ItemTypeEnum> m_itemType;
    private List<ProductListModel> m_productListModelArrayList;
    private ProductListAdapter m_productListAdapter;
    private ProductListFragment m_mainFragment;
    private ProductDetailsFragment m_fragmentProductDetails;
    @Inject
    private ItemClientService m_itemService;
    private ImageView iFilter;

    @Inject
    public ProductListView() {

    }

    public VariableValueChange<ItemPbOuterClass.ItemTypeEnum> getItemType() {
        return m_itemType;
    }

    public void setItemType(ItemPbOuterClass.ItemTypeEnum itemtype) {
        m_itemType.setVar(itemtype);
    }

    public ImageView getiFilter() {
        return iFilter;
    }

    public void setiFilter(ImageView iFilter) {
        this.iFilter = iFilter;
    }

    public ProductListFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(ProductListFragment fragment) {
        m_mainFragment = fragment;
    }


    public List<ProductListModel> getProductListModelArrayList() {
        return m_productListModelArrayList;
    }

    public void setProductListModelArrayList(List<ProductListModel> productListModelArrayList) {
        m_productListModelArrayList = productListModelArrayList;
    }

    public ProductListAdapter getProductListAdapter() {
        return m_productListAdapter;
    }

    public void setProductListAdapter(ProductListAdapter productListAdapter) {
        m_productListAdapter = productListAdapter;
    }

    public void likeUnLike(int position) {
        if (getProductListModelArrayList().get(position).isLike()) {
            getProductListModelArrayList().get(position).setLike(false);
        } else {
            getProductListModelArrayList().get(position).setLike(true);
        }

        getProductListAdapter().notifyDataSetChanged();
    }

    public void addToCart(boolean addTocart, int position) {
        if (addTocart) {
            int totalKg = getProductListModelArrayList().get(position).getTotalKg();
            totalKg = totalKg + 1;
            getProductListModelArrayList().get(position).setTotalKg(totalKg);

        } else {
            int totalKg = getProductListModelArrayList().get(position).getTotalKg();

            if (totalKg < 1) {
                getProductListModelArrayList().get(position).setTotalKg(0);
            } else {
                totalKg = totalKg - 1;
                getProductListModelArrayList().get(position).setTotalKg(totalKg);
            }


        }

        getProductListAdapter().notifyDataSetChanged();

    }

    public void getItemList(ItemPbOuterClass.ItemTypeEnum itemTypeEnum) {
        ItemPbOuterClass.ItemSearchRequestPb.Builder builder = ItemPbOuterClass.ItemSearchRequestPb.newBuilder();
        builder.setItemType(itemTypeEnum);
        builder.setAvailabilityStatus(ItemPbOuterClass.AvailabilityStatusEnum.AVAILABLE);
        ItemPbOuterClass.ItemSearchResponsePb result = null;
        try {
            result = m_itemService.search(builder.build());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (result != null) {
            Log.e("RES", String.valueOf(result.getResultsCount()));
            getProductListModelArrayList().addAll(m_listConvertor.getListModel(result));
        }
    }


    public void setProductDetailsFragment(ProductDetailsFragment fragmentProductDetails) {
        m_fragmentProductDetails = fragmentProductDetails;
    }

    public ProductDetailsFragment getProductDetailsFragment() {
        return m_fragmentProductDetails;
    }
}
