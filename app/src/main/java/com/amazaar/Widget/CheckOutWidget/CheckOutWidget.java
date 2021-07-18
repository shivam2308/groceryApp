package com.amazaar.Widget.CheckOutWidget;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Adapters.AddressListAdapter;
import com.amazaar.Adapters.OrderSummaryMiniListAdapter;
import com.amazaar.Fragments.PaymentFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import javax.inject.Inject;

import roboguice.RoboGuice;


public class CheckOutWidget extends LinearLayout implements IView<CheckOutView>, View.OnClickListener{

    @Inject
    public CheckOutView m_view;

    //Declaration
    private RelativeLayout rlConfirmOreder;
    private TextView m_total;
    private RecyclerView rvAddressList;
    private RecyclerView rvOrderList;
    private LinearLayoutManager mLayoutManager;
    private AddressListAdapter addressListAdapterNew;
    private OrderSummaryMiniListAdapter orderListAdapter;


    public CheckOutWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.checkout_layout, this);
        mLayoutManager = new LinearLayoutManager(getContext());
        rlConfirmOreder=findViewById(R.id.fragment_checkout_btnConfirmOrder);
        rvAddressList = findViewById(R.id.fragment_checkout_rvAddressList);
        m_total = findViewById(R.id.fragment_order_list_tvTotalKg);
        rvAddressList.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));
        rvOrderList = findViewById(R.id.fragment_checkout_rvOrderList);
        rvOrderList.setLayoutManager(mLayoutManager);


        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.checkout_layout, this);
        rlConfirmOreder.setOnClickListener(this);

    }


    public void initWidget() {
        addressListAdapterNew = new AddressListAdapter(getView().getAddressListModel(), getContext());
        rvAddressList.setAdapter(addressListAdapterNew);
        orderListAdapter = new OrderSummaryMiniListAdapter(getView().getOrderListModel(), getContext());
        rvOrderList.setAdapter(orderListAdapter);
        m_total.setText(getView().getTotalPrice());
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }




    @Override
    public CheckOutView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {
        //super.onClick(v);
        if(v==rlConfirmOreder)
        {
            openFragment();
        }
    }

    private void openFragment()
    {
        PaymentFragment paymentFragment =new PaymentFragment();
        Bundle bundle=new Bundle();
        bundle.putString("totalAmount", getView().getTotalPrice());
        //set Fragmentclass Arguments
        paymentFragment.setArguments(bundle);
        Utils.addNextFragment(getContext(), paymentFragment, getView().getMainFragment(), true);
    }
}