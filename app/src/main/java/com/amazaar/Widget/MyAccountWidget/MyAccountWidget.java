package com.amazaar.Widget.MyAccountWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Interfaces.IView;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.amazaar.Widget.MenuWIdget.MenuView;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import de.hdodenhof.circleimageview.CircleImageView;
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
    private RelativeLayout rlChangePsw;
    private RelativeLayout rlHistory;
    private RelativeLayout rlDeActive;
   //* private ProfileAddressListAdapter addressListAdapterNew;
   // private List<AddressListModelNew> addressListModelNewList;
    private RecyclerView rvAddress;
   /* private LinearLayoutManager mLayoutManager;*/


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
        ivProfile = (ImageView) findViewById(R.id.fragment_myaccount_ivProfile);
        tvEdit = (ImageView) findViewById(R.id.fragment_myaccount_tvEdit);
        rlChangePsw = (RelativeLayout) findViewById(R.id.fragment_myaccount_rlChangePsw);
        rlHistory = (RelativeLayout) findViewById(R.id.fragment_myaccount_rlCleanHistory);
        rlDeActive = (RelativeLayout) findViewById(R.id.fragment_myaccount_rlDeActive);
        //rvAddress = (RecyclerView) rootView.findViewById(R.id.fragment_myaccount_rvAddress);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.my_account_layout, this);
        tvEdit.setOnClickListener(this);
        rlChangePsw.setOnClickListener(this);
        rlHistory.setOnClickListener(this);
        rlDeActive.setOnClickListener(this);
        rvAddress.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        setAddress();
    }

    private void initWidget() {

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public MyAccountView getView() {
        return m_view;
    }

    private void setAddress() {


      /*  addressListModelNewList = new ArrayList<>();
        TempListData tempListData = new TempListData();
        addressListModelNewList = tempListData.getCheckoutAddress();

        addressListAdapterNew = new ProfileAddressListAdapter(addressListModelNewList, getActivity(), MyAccountFragment.this);
        rvAddress.setAdapter(addressListAdapterNew);*/


    }

    @Override
    public void onClick(View v) {

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
        }
        else if(v==tvEdit)
        {
            DialogFragmentProfile dialogFragmentProfile = new DialogFragmentProfile();
            dialogFragmentProfile.show(getFragmentManager(), getString(R.string.lbl_choosprofile));
        }*/

    }

    /**
     * SetUp Toolbar & title
     */
    public void initToolbar() {
      //  ((HomeActivity) getActivity()).setUpToolbar(getString(R.string.nav_my_account), false,true,false,false);
    }



}
