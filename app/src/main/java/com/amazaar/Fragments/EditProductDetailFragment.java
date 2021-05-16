package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.Protobuff.ItemPbOuterClass;
import com.amazaar.R;
import com.amazaar.Widget.EditProductDetailWidget.EditProductDetailWidget;

public class EditProductDetailFragment<itemPb> extends BaseFragment {
    private EditProductDetailWidget m_editProductDetailWidget;
    private ItemPbOuterClass.ItemPb m_itempb;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_edit_product_detail, container, false);
        initToolbar();
        initComponents(rootView);
        return rootView;
    }
    @Override
    public void initComponents(View rootView) {
        m_editProductDetailWidget = rootView.findViewById(R.id.editproductdetail_widget);
        m_editProductDetailWidget.getView().setMainFragment(this);
        m_editProductDetailWidget.getView().getItemPbMOdel().setVar(m_itempb);
    }

    public EditProductDetailWidget getEditProductDeatilWidget(){
        return m_editProductDetailWidget;
    }
    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.PRODUCT_DETAILS_EDIT);

    }
    public  void setItemPb(ItemPbOuterClass.ItemPb pb){
        m_itempb = pb;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            if (((HomeActivity) getActivity()) != null) {
                initToolbar();
            }
        }
    }
}
