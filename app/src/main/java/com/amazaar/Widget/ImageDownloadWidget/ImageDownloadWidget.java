package com.amazaar.Widget.ImageDownloadWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.R;
import com.budiyev.android.imageloader.ImageLoader;
import com.google.inject.Injector;
import com.prod.basic.common.code.Strings;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class ImageDownloadWidget extends LinearLayout implements IView<ImageDownloadView> {

    @Inject
    public ImageDownloadView m_view;
    private View m_image;

    public ImageDownloadWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.download_image_layout, this);
        m_image = (ImageView) findViewById(R.id.downloadimage);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.download_image_layout, this);

    }

    private void initWidget() {
        getView().getImageUrl().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                if (Strings.notEmpty(getView().getImageUrl().getVar())) {
                    ImageLoader.with(getContext()).from(getView().getImageUrl().getVar()).load(m_image);
                }
            }
        });
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public ImageDownloadView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }
}
