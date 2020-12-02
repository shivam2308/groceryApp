package com.amazaar.Widget.CartWidget;

import android.app.FragmentManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Fragments.CartListFragment;
import com.amazaar.Fragments.CheckOutFragment;
import com.amazaar.Fragments.MenuFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class CartWidget extends LinearLayout implements IView<CartView>, View.OnClickListener {

    @Inject
    public CartView m_view;
    private RecyclerView rvProductList;
    private TextView tvTotalPrice;
    private RelativeLayout rlCheckOut;
    private RelativeLayout rlEmpty;
    private LinearLayout llSelectPin;
    private ImageView ivClose;
    private ImageView ivHome;
    private TextView tvCartCount;
    private  TextView m_chkbtn;
    private CheckOutFragment checkOutFragment;

    /*private LinearLayoutManager mLayoutManager;
    private CartListAdapter productListAdapter;
    private List<CartlistModel> productListModelArrayList;*/
    private MenuItem item;

    public CartWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.cart_layout, this);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.cart_layout, this);
        rvProductList = (RecyclerView) findViewById(R.id.fragment_cartlist_rvProductList);
        rlCheckOut = (RelativeLayout) findViewById(R.id.fragment_cartlist_rlCheckOut);
        rlEmpty = (RelativeLayout) findViewById(R.id.fragment_cartlist_rlEmpty);
        tvTotalPrice = (TextView) findViewById(R.id.fragment_cartlist_tvTotalKg);
        tvCartCount = (TextView) findViewById(R.id.fragment_cartlist_tvCartCount);
        ivClose = (ImageView) findViewById(R.id.fragment_cartlist_ivClose);
        ivHome = (ImageView) findViewById(R.id.fragment_cartlist_ivHome);
        llSelectPin = (LinearLayout) findViewById(R.id.fragment_cartlist_llSelectPin);
        m_chkbtn = (TextView) findViewById(R.id.checkout_btn);

       // mLayoutManager = new LinearLayoutManager(getActivity());
       // rvProductList.setLayoutManager(mLayoutManager);
        rlCheckOut.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        llSelectPin.setOnClickListener(this);
        m_chkbtn.setOnClickListener(this);
    }

    private void initWidget() {

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public CartView getView() {
        return m_view;
    }

    @Override
    public void onClick(View v) {
        //Utils.hideKeyboard(getContext());
        if (v == m_chkbtn) {
            CheckOutFragment checkOutFragment = new CheckOutFragment();
            Utils.addNextFragment(getContext(), checkOutFragment, getView().getMainFragment(), true);
        }
        /*else if (v == llSelectPin) {

            DialogFragmentChoosPincode dialogFragmentChoosPincode = new DialogFragmentChoosPincode();
            dialogFragmentChoosPincode.show(getFragmentManager(), getString(R.string.choospincode));

        } else if (v == ivClose) {
            getFragmentManager().popBackStack();
        } else if (v == ivHome) {
            getFragmentManager().popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE);
        }*/

    }
}
