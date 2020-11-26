package com.amazaar.Widget.ImageDownloadWidget;

import com.amazaar.ListnerAndInputHandlers.VariableValueChange;

import javax.inject.Inject;

public class ImageDownloadView {

    private VariableValueChange<String> m_imageUrl;

    @Inject
    public ImageDownloadView(VariableValueChange<String> imageUrl) {
        m_imageUrl = imageUrl;
    }

    public VariableValueChange<String> getImageUrl() {
        return m_imageUrl;
    }
}
