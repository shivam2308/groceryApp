package com.amazaar.Widget.OrderListWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Adapters.OrderListAdapter;
import com.amazaar.CommonCode.TempListData;
//import com.amazaar.Fragments.OrderSummaryFragment;
import com.amazaar.CustomeComponent.CustomTextView;
import com.amazaar.Fragments.OrderSummaryFragment;
import com.amazaar.Fragments.ProductListFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListModels.OrderListModel;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class OrderListWidget extends LinearLayout implements IView<OrderListView>, View.OnClickListener {

    @Inject
    public OrderListView m_view;

    //Declaration
    private RecyclerView rvProductList;
    private GridLayoutManager mLayoutManager;
    private OrderListAdapter orderListAdapter;
    private List<OrderListModel> orderListModelList;
    private MenuItem item;
    private CustomTextView empty_order_list;

    public OrderListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.order_list_layout, this);
        rvProductList = (RecyclerView) findViewById(R.id.fragment_orderlist_rvOrder);
        empty_order_list = (CustomTextView) findViewById(R.id.empty_order_list);
        mLayoutManager = new GridLayoutManager(getContext(), 1);
        rvProductList.setLayoutManager(mLayoutManager);


        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void getListData() {
        orderListModelList = getView().getOrderlistResp();
        orderListAdapter = new OrderListAdapter(orderListModelList, getContext());
        rvProductList.setAdapter(orderListAdapter);
    }

//    @Override
//    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
//        super.onCreateOptionsMenu(menu, inflater);
//        item = menu.findItem(R.id.menu_left);
//        item.setVisible(false);
//
//    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.order_list_layout, this);
        //rlConfirmOreder.setOnClickListener(this);

    }

    public void initWidget() {
        getView().getIsaAvaliable().setListener(new VariableValueChange.ChangeListener(){
            @Override
            public void onChange() {
                if(getView().getIsaAvaliable().getVar() == true){
                    rvProductList.setVisibility(VISIBLE);
                    empty_order_list.setVisibility(GONE);
                }
                else{
                    empty_order_list.setVisibility(VISIBLE);
                    rvProductList.setVisibility(GONE);
                }
            }
        });
        getListData();
        orderListAdapter.setOnItemClickListener(new OrderListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, OrderListModel viewModel) {
                OrderSummaryFragment fragmentProductDetails = new OrderSummaryFragment();
                fragmentProductDetails.setModel(viewModel);
                Utils.addNextFragment(getContext(),fragmentProductDetails, getView().getMainFragment(), false);
           }
        });

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }


    @Override
    public OrderListView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {

    }
}
