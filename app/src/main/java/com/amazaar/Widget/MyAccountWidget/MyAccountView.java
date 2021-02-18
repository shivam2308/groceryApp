package com.amazaar.Widget.MyAccountWidget;

import com.amazaar.Fragments.MyAccountFragment;
import com.amazaar.Fragments.UploadImageFragment;

import javax.inject.Inject;

public class MyAccountView {

    private UploadImageFragment m_uploadImageFragment;
    private MyAccountFragment m_mainFragment;

    @Inject
    MyAccountView() {

    }

    public MyAccountFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(MyAccountFragment fragment) {
        m_mainFragment = fragment;
    }

    public void setUploadFragment(UploadImageFragment uploadImageFragment) {
        m_uploadImageFragment = uploadImageFragment;
    }
    public UploadImageFragment getUploadFragment() {
        return m_uploadImageFragment;
    }
}
