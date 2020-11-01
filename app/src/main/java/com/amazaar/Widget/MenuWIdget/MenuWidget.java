package com.amazaar.Widget.MenuWIdget;

import android.app.FragmentManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.amazaar.Interfaces.IView;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;
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
    private CardView cvLoginRegister;
    private CardView cvProfile;
    private CircleImageView ivProfile;


    public MenuWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.menu_layout, this);
        ivClose = (ImageView) findViewById(R.id.fragment_menu_ivClose);
        ivLocation = (ImageView) findViewById(R.id.fragment_menu_ivLocation);
        tvLogin = (TextView) findViewById(R.id.fragment_menu_tvLogin);
        tvRegister = (TextView) findViewById(R.id.fragment_menu_tvSignup);
        rlProfile = (RelativeLayout) findViewById(R.id.fragment_menu_rlProfile);
        cvLoginRegister = (CardView) findViewById(R.id.fragment_menu_cvLoginRegister);
        cvProfile = (CardView) findViewById(R.id.fragment_menu_cvProfile);
        ivProfile = (CircleImageView) findViewById(R.id.fragment_menu_ivProfile);
        tvName = (TextView) findViewById(R.id.fragment_menu_tvName);
        tvCity = (TextView) findViewById(R.id.fragment_menu_tvCity);
        rlWhishList = (RelativeLayout) findViewById(R.id.fragment_menu_rlWishlist);
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
        rlWhishList.setOnClickListener(this);
        rlOrderHistory.setOnClickListener(this);
        rlLogout.setOnClickListener(this);
        ivLocation.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
        tvRegister.setOnClickListener(this);
        cvProfile.setOnClickListener(this);
        rlSetting.setOnClickListener(this);
    }

    private void initWidget() {

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
    public void onClick(View v) {
        Utils.hideKeyboard(getContext());

        if (v == ivClose) {
            AmazaarApplication.getFragmentManager().popBackStack();
        }
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

        }
        else if (v == cvProfile) {

            MyAccountFragment profileFragmentNew = new MyAccountFragment();
            Utils.addNextFragment(getActivity(), profileFragmentNew, MenuFragment.this, false);
        }
        else if(v==rlLogout)
        {
            GrocerApplication.getmInstance().savePreferenceDataBoolean(getString(R.string.preferances_islogin), false);
            checkLogin();
        }
        else if(v==rlHelp)
        {
            HelpFragment helpFragment = new HelpFragment();
            Utils.addNextFragment(getActivity(), helpFragment, MenuFragment.this, false);
        }
        else if(v==rlSetting)
        {
            SettingFragment settingFragment = new SettingFragment();
            Utils.addNextFragment(getActivity(), settingFragment, MenuFragment.this, false);
        }
        else if(v==rlOrderHistory)
        {
            OrderListFragment orderListFragment = new OrderListFragment();
            Utils.addNextFragment(getActivity(), orderListFragment, MenuFragment.this, false);
        }
        else if(v==rlWhishList)
        {
            ProductListFragment fragmentProductDetails = new ProductListFragment();
            fragmentProductDetails.setTargetFragment(this,99);
            Utils.addNextFragment(getActivity(), fragmentProductDetails, MenuFragment.this, false);
        }*/




    }

    public void initToolbar() {
       // ((HomeActivity) getActivity()).setUpToolbar(getString(R.string.nav_menu_home), false, false, false,false);
    }


}
