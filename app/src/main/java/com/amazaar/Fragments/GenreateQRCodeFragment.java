package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.ListModels.OrderSummaryListModel;
import com.amazaar.R;
import com.amazaar.Widget.GenreateQRCodeWidget.GenreateQRCodeWidget;

import java.util.List;

public class GenreateQRCodeFragment extends BaseFragment {

    private GenreateQRCodeWidget m_genreateQRCodeWidget;
    private List<OrderSummaryListModel> m_orderSummaryListModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_qr_code, container, false);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }

    @Override
    public void initComponents(View rootView) {
        m_genreateQRCodeWidget = rootView.findViewById(R.id.qr_code_frag);
        m_genreateQRCodeWidget.getView().setMainFragment(this);
        initToolbar();
        m_genreateQRCodeWidget.getView().getListModel().setVar(m_orderSummaryListModel);
    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.CART);

    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            try {
                initToolbar();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    public void setListModel(List<OrderSummaryListModel> orderSummaryListModel) {
        m_orderSummaryListModel = orderSummaryListModel;
    }
}
