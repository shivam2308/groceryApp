package com.amazaar.Widget.UploadImageWIdget;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amazaar.Interfaces.IView;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.R;
import com.google.inject.Injector;

import java.io.IOException;

import javax.inject.Inject;

import roboguice.RoboGuice;

import static android.app.Activity.RESULT_OK;

public class UploadWidget extends LinearLayout implements IView<UploadView> {

    @Inject
    public UploadView m_view;
    private ImageView m_image;
    private Button m_choose;
    private Button m_upload;


    public UploadWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.upload_image_layout, this);
        m_image = (ImageView) findViewById(R.id.uploadImg);
        m_choose = (Button) findViewById(R.id.btnChoose);
        m_upload = (Button) findViewById(R.id.btnUpload);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.upload_image_layout, this);

    }

    public void setImageType(ImagePbOuterClass.ImageTypeEnum imageType) {
        getView().setImageType(imageType);
    }

    public void setImageId(String imageId) {
        getView().setImageId(imageId);
    }

    private void initWidget() {
        m_choose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().chooseImage(getContext());
            }
        });

        m_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getView().uploadImage(getContext());
            }
        });
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public UploadView getView() {
        return m_view;
    }

    //this method must  call in Activity
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == getView().getPICK_IMAGE_REQUEST() && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            getView().setFilePath(data.getData());
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), getView().getFilePath());
                m_image.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
