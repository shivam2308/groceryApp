package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.QRCodeReaderWidget.QRCodeReaderWidget;

public class QRCodeReaderFragment extends BaseFragment {
    private QRCodeReaderWidget m_qr_reader_widget;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View rootView = inflater.inflate(R.layout.fragment_qr_reader_list, container, false);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }


    @Override
    public void initComponents(View rootView) {
        m_qr_reader_widget = rootView.findViewById(R.id.qrreaderwidget);
        m_qr_reader_widget.getView().setMainFragment(this);
        initToolbar();
    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.CART);

    }

    public QRCodeReaderWidget getQRCodeReaderWidget() {
        return m_qr_reader_widget;
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);

        if (!hidden) {
            try {
                initToolbar();
                m_qr_reader_widget.initWidget();
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }

    
}
