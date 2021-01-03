package com.amazaar.Widget.ProfileEditWidget;

import com.amazaar.Fragments.ProfileEditFragment;

import javax.inject.Inject;

public class ProfileEditView {
    @Inject
    private ProfileEditFragment m_mainFragment;

    @Inject
    public ProfileEditView() {

    }

    public ProfileEditFragment getMainFragment() {
        return m_mainFragment;
    }

    public void setMainFragment(ProfileEditFragment profileEditFragment) {
        m_mainFragment = profileEditFragment;
    }
}
