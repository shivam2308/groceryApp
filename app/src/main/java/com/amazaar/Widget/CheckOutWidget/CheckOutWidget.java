package com.amazaar.Widget.CheckOutWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Adapters.HomeProductListAdapter;
import com.amazaar.Fragments.PaymentFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;


public class CheckOutWidget extends LinearLayout implements IView<CheckOutView>, View.OnClickListener{

    @Inject
    public CheckOutView m_view;

    //Declaration
    private RelativeLayout rlConfirmOreder;
    private RecyclerView rvAddressList;
    private RecyclerView rvOrderList;
    private LinearLayoutManager mLayoutManager;
//    private AddressListAdapter addressListAdapterNew;
//    private OrderSummaryListAdapter orderListAdapter;
//    private List<AddressListModelNew> addressListModelNewList;
//    private List<OrderListModelNew> orderListModelNewList;

    public CheckOutWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.checkout_layout, this);
        mLayoutManager = new LinearLayoutManager(getContext());
        rlConfirmOreder=findViewById(R.id.fragment_checkout_btnConfirmOrder);
        rvAddressList = findViewById(R.id.fragment_checkout_rvAddressList);
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


    private void initWidget() {

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    private void setAddress() {

    }



    @Override
    public CheckOutView getView() {
        return m_view;
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
        Utils.addNextFragment(getContext(), paymentFragment, getView().getMainFragment(), true);
    }
}