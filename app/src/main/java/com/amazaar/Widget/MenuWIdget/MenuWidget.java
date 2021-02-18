package com.amazaar.Widget.MenuWIdget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.ControlFlow.GetImageFromUrl;
import com.amazaar.Fragments.HelpFragment;
import com.amazaar.Fragments.MyAccountFragment;
import com.amazaar.Fragments.OrderListFragment;
import com.amazaar.Fragments.SettingFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.CustomerPbOuterClass;
import com.amazaar.R;
import com.amazaar.SessionManager.CustomerSession;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class MenuWidget extends LinearLayout implements IView<MenuView>, View.OnClickListener {
    @Inject
    public MenuView m_view;
    private ImageView ivClose;
    private ImageView ivLocation;
    private TextView tvLogin;
    private TextView tvRegister;
    private TextView tvName;
    private TextView tvCity;
    private RelativeLayout rlProfile;
    private RelativeLayout rlWhishList;
    private RelativeLayout rlOrderHistory;
    private RelativeLayout rlSetting;
    private RelativeLayout rlHelp;
    private RelativeLayout rlLogout;
    private CardView m_admin;
    private CardView cvProfile;
    private ImageView ivProfile;
    @Inject
    private CustomerSession m_customerSession;
    @Inject
    private GetImageFromUrl m_imageUrl;


    public MenuWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.menu_layout, this);
        //ivClose = (ImageView) findViewById(R.id.fragment_menu_ivClose);
        //ivLocation = (ImageView) findViewById(R.id.fragment_menu_ivLocation);
        tvLogin = (TextView) findViewById(R.id.fragment_menu_tvLogin);
        tvRegister = (TextView) findViewById(R.id.fragment_menu_tvSignup);
        rlProfile = (RelativeLayout) findViewById(R.id.fragment_menu_rlProfile);
        m_admin = (CardView) findViewById(R.id.adminCard);
        cvProfile = (CardView) findViewById(R.id.fragment_menu_cvProfile);
        ivProfile = (ImageView) findViewById(R.id.fragment_menu_ivProfile);
        tvName = (TextView) findViewById(R.id.fragment_menu_tvName);
        tvCity = (TextView) findViewById(R.id.fragment_menu_tvCity);
        //rlWhishList = (RelativeLayout) findViewById(R.id.fragment_menu_rlWishlist);
        rlOrderHistory = (RelativeLayout) findViewById(R.id.fragment_menu_rlOrderHistory);
        rlSetting = (RelativeLayout) findViewById(R.id.fragment_menu_rlSetting);
        rlHelp = (RelativeLayout) findViewById(R.id.fragment_menu_rlHelp);
        rlLogout = (RelativeLayout) findViewById(R.id.fragment_menu_rlLogout);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.menu_layout, this);
        rlHelp.setOnClickListener(this);
        //rlWhishList.setOnClickListener(this);
        rlOrderHistory.setOnClickListener(this);
        rlLogout.setOnClickListener(this);
        //ivLocation.setOnClickListener(this);
        //ivClose.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        cvProfile.setOnClickListener(this);
        rlSetting.setOnClickListener(this);
    }

    private void initWidget() {
        if (m_customerSession.getSession() != null) {
            tvLogin.setVisibility(GONE);
            cvProfile.setVisibility(VISIBLE);
            m_imageUrl.setImageFromUrl(getContext(), m_customerSession.getSession().getProfileImage(), ivProfile, DefaultImageUrl.ImageShowTypeEnum.MALE_PROFILE);
            tvName.setText(com.amazaar.CommonCode.Strings.titleCase(m_customerSession.getSession().getName()));
            tvCity.setText(com.amazaar.CommonCode.Strings.toTitileCaseFormatter(m_customerSession.getSession().getAddress().getCity().name()));
            if (m_customerSession.getSession().getPrivilege() == CustomerPbOuterClass.PrivilegeTypeEnum.ADMIN) {
                m_admin.setVisibility(VISIBLE);
            } else {
                m_admin.setVisibility(GONE);
            }
        }
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public MenuView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        Utils.hideKeyboard(getContext());

//        if (v == ivClose) {
//            AmazaarApplication.getFragmentManager().popBackStack();
//        }
       /* else if (v == tvLogin) {

            LoginSignUpFragment menuFragment = new LoginSignUpFragment();
            Utils.addNextFragment(getActivity(), menuFragment, MenuFragment.this, false);
        }
        else if (v == tvRegister) {

            LoginSignUpFragment menuFragment = new LoginSignUpFragment();
            Utils.addNextFragment(getActivity(), menuFragment, MenuFragment.this, false);
        }
        else if (v == ivLocation) {
            DialogFragmentChoosPincode dialogFragmentChoosPincode = new DialogFragmentChoosPincode();
            dialogFragmentChoosPincode.show(getFragmentManager(), getString(R.string.choospincode));

        }*/
        if (v == cvProfile) {

            MyAccountFragment profileFragmentNew = new MyAccountFragment();
            getView().setMyAccountFragment(profileFragmentNew);
            Utils.addNextFragment(getContext(), profileFragmentNew, getView().getMainFragment(), false);
        }
//        else if(v==rlLogout)
//        {
//            GrocerApplication.getmInstance().savePreferenceDataBoolean(getString(R.string.preferances_islogin), false);
//            checkLogin();
//        }
        else if (v == rlHelp) {
            HelpFragment helpFragment = new HelpFragment();
            Utils.addNextFragment(getContext(), helpFragment, getView().getMainFragment(), true);
        } else if (v == rlSetting) {
            SettingFragment settingFragment = new SettingFragment();
            Utils.addNextFragment(getContext(), settingFragment, getView().getMainFragment(), true);
        } else if (v == rlOrderHistory) {
            OrderListFragment orderListFragment = new OrderListFragment();
            Utils.addNextFragment(getContext(), orderListFragment, getView().getMainFragment(), true);
        }
//        else if(v==rlWhishList)
//        {
//            ProductListFragment fragmentProductDetails = new ProductListFragment();
//            fragmentProductDetails.setTargetFragment(this,99);
//            Utils.addNextFragment(getActivity(), fragmentProductDetails, MenuFragment.this, false);
//        }


    }

    public void initToolbar() {
        // ((HomeActivity) getActivity()).setUpToolbar(getString(R.string.nav_menu_home), false, false, false,false);
    }


}
