package com.amazaar.Widget.CartWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Adapters.CartListAdapter;
import com.amazaar.Convertors.CartPbToCartListModelConvertor;
import com.amazaar.DatabaseEnitityHelper.CartEntityDaoHelper;
import com.amazaar.Fragments.CheckOutFragment;
import com.amazaar.Handlers.CartItemHandler;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListModels.CartListModel;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;
import com.prod.basic.common.collect.Lists;

import java.util.ArrayList;
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
    private TextView m_chkbtn;
    private CheckOutFragment checkOutFragment;
    private LinearLayoutManager mLayoutManager;
    @Inject
    private CartListAdapter productListAdapter;
    private List<CartListModel> productListModelArrayList;
    private MenuItem item;
    @Inject
    private CartItemHandler m_cartHandler;

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

        mLayoutManager = new LinearLayoutManager(getContext());
        rvProductList.setLayoutManager(mLayoutManager);

    }

    private void initWidget() {
        rlCheckOut.setOnClickListener(this);
        ivClose.setOnClickListener(this);
        ivHome.setOnClickListener(this);
        llSelectPin.setOnClickListener(this);
        m_chkbtn.setOnClickListener(this);
        productListModelArrayList = new ArrayList<>();
        getView().setCartListAdapter(productListAdapter);
        getView().setCartListModel(productListModelArrayList);
        getView().getCartList();
        productListAdapter = new CartListAdapter(getContext(), productListModelArrayList, this);
        rvProductList.setAdapter(productListAdapter);
        tvTotalPrice.setText(getContext().getString(R.string.dolar) + getView().getTotalPrice(getView().getCartListModel()));
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    public void addToCart(boolean addTocart, int position) {
        if (addTocart) {
            int totalKg = Integer.parseInt(productListModelArrayList.get(position).getKg());
            totalKg = totalKg + 1;
            productListModelArrayList.get(position).getOnitemChange().setVar(getView().getUpdatedCartItem(productListModelArrayList.get(position).getOnitemChange().getData(),totalKg));
            m_cartHandler.handle(productListModelArrayList.get(position),position);
        } else {
            int totalKg = Integer.parseInt(productListModelArrayList.get(position).getKg());

            if (totalKg < 1) {
                productListModelArrayList.get(position).getOnitemChange().setVar(getView().getUpdatedCartItem(productListModelArrayList.get(position).getOnitemChange().getData(),totalKg));
                m_cartHandler.deleteItemFromCart(position);
            } else {
                totalKg = totalKg - 1;
                productListModelArrayList.get(position).getOnitemChange().setVar(getView().getUpdatedCartItem(productListModelArrayList.get(position).getOnitemChange().getData(),totalKg));
                m_cartHandler.handle(productListModelArrayList.get(position),position);
            }

        }

        productListAdapter.notifyDataSetChanged();
        getView().getCartList();
        tvTotalPrice.setText(getContext().getString(R.string.dolar) + getView().getTotalPrice(getView().getCartListModel()));

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
