package com.amazaar.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.amazaar.Activity.HomeActivity;
import com.amazaar.Enums.TopBarUiEnum;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.R;
import com.amazaar.Widget.UploadImageWIdget.UploadWidget;

public class UploadImageFragment extends BaseFragment {

    private UploadWidget m_uploadWidget;
    private ImagePbOuterClass.ImageTypeEnum m_ImageType;
    private String m_imageId;

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

    public void setImageType(ImagePbOuterClass.ImageTypeEnum imageType) {
        m_ImageType = imageType;
    }

    public void setImageId(String imageId) {
        m_imageId = imageId;
    }

    @Override
    public void initComponents(View rootView) {
        m_uploadWidget = (UploadWidget) rootView.findViewById(R.id.uploadImageWidget);
        m_uploadWidget.setImageId(m_imageId);
        m_uploadWidget.setImageType(m_ImageType);
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        if (!hidden) {
            initToolbar();
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        m_uploadWidget.onActivityResult(requestCode,resultCode,data);
    }
}
