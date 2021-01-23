package com.amazaar.Widget.MyAccountWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.ControlFlow.GetImageFromUrl;
import com.amazaar.Fragments.ProfileEditFragment;
import com.amazaar.Fragments.UploadImageFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.R;
import com.amazaar.SessionManager.CustomerSession;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class MyAccountWidget extends LinearLayout implements IView<MyAccountView>, View.OnClickListener {
    @Inject
    public MyAccountView m_view;
    private TextView tvName;
    private TextView tvPhone;
    private TextView tvEmail;
    private TextView tvChangePsw;
    private ImageView ivProfile;
    private ImageView tvEdit;
    //    private RelativeLayout rlChangePsw;
//    private RelativeLayout rlHistory;
    private RelativeLayout rlDeActive;
    private TextView m_address;
    @Inject
    private CustomerSession m_customerSession;
    @Inject
    private GetImageFromUrl m_imageUrl;


    public MyAccountWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.my_account_layout, this);
        tvName = (TextView) findViewById(R.id.fragment_myaccount_tvName);
        tvEmail = (TextView) findViewById(R.id.fragment_myaccount_tvEmail);
        tvPhone = (TextView) findViewById(R.id.fragment_myaccount_tvPhone);
        tvChangePsw = (TextView) findViewById(R.id.fragment_myaccount_tvChange);
        m_address = (TextView) findViewById(R.id.fragment_myaccount_rvAddress);
        ivProfile = (ImageView) findViewById(R.id.fragment_myaccount_ivProfile);
        tvEdit = (ImageView) findViewById(R.id.fragment_myaccount_tvEdit);
        //rlChangePsw = (RelativeLayout) findViewById(R.id.fragment_myaccount_rlChangePsw);
        //rlHistory = (RelativeLayout) findViewById(R.id.fragment_myaccount_rlCleanHistory);
        rlDeActive = (RelativeLayout) findViewById(R.id.fragment_myaccount_rlDeActive);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.my_account_layout, this);
        tvEdit.setOnClickListener(this);
//        rlChangePsw.setOnClickListener(this);
//        rlHistory.setOnClickListener(this);
        rlDeActive.setOnClickListener(this);
    }

    private void initWidget() {
        m_imageUrl.setImageFromUrl(getContext(), m_customerSession.getSession().getProfileImage(), ivProfile, DefaultImageUrl.ImageShowTypeEnum.MALE_PROFILE);
        tvName.setText(com.amazaar.CommonCode.Strings.titleCase(m_customerSession.getSession().getName()));
        tvPhone.setText(m_customerSession.getSession().getContact().getMobile().getMobileNo());
        tvEmail.setText(com.amazaar.CommonCode.Strings.getEmail(m_customerSession.getSession().getContact().getEmail()));
        m_address.setText(com.amazaar.CommonCode.Strings.getAddress(m_customerSession.getSession().getAddress()));

        tvChangePsw.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                UploadImageFragment uploadImageFragment = new UploadImageFragment();
                uploadImageFragment.setImageId(m_customerSession.getSession().getDbInfo().getId());
                uploadImageFragment.setImageType(ImagePbOuterClass.ImageTypeEnum.PROFILE_IMAGE);
                Utils.addNextFragmentFadeAnim(uploadImageFragment, getView().getMainFragment());
                return false;
            }
        });
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public MyAccountView getView() {
        return m_view;
    }


    @Override
    public void onClick(View v) {
//        if (v == tvChangePsw) {
//
//        }
       /* if(v==rlChangePsw)
        {
            DialogFragmentChangePsw dialogFragmentChangePsw = new DialogFragmentChangePsw();
            dialogFragmentChangePsw.show(getFragmentManager(), getString(R.string.lbl_changepassword));
        }
        else if(v==rlDeActive)
        {
            DialogDeActiveAccount dialogDeActiveAccount = new DialogDeActiveAccount();
            dialogDeActiveAccount.show(getFragmentManager(), getString(R.string.lbl_deactiveaccount));
        }
        else if(v==rlHistory)
        {
            DialogFragmentClearHistory dialogFragmentClearHistory = new DialogFragmentClearHistory();
            dialogFragmentClearHistory.show(getFragmentManager(), getString(R.string.lbl_clearhistory));
        }*/
        if (v == tvEdit) {
            ProfileEditFragment ProfileEditFragment = new ProfileEditFragment();
            Utils.addNextFragment(getContext(), ProfileEditFragment, getView().getMainFragment(), true);
        }

    }

    /**
     * SetUp Toolbar & title
     */
    public void initToolbar() {
        //  ((HomeActivity) getActivity()).setUpToolbar(getString(R.string.nav_my_account), false,true,false,false);
    }


}
