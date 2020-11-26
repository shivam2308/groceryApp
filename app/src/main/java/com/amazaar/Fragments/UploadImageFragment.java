package com.amazaar.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.R;
import com.amazaar.Widget.UploadImageWIdget.UploadWidget;

public class UploadImageFragment  extends BaseFragment{

    private UploadWidget m_uploadWidget;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        final View rootView = inflater.inflate(R.layout.fragment_upload_profile, container, false);
        initToolbar();
        initComponents(rootView);

        return rootView;
    }

    public void initToolbar() {
        ((HomeActivity) getActivity()).setToolbar(TopBarUiEnum.PROFILE);
    }

    @Override
    public void initComponents(View rootView) {
        m_uploadWidget = (UploadWidget) rootView.findViewById(R.id.uploadImageWidget);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initToolbar();
        }
    }
}
