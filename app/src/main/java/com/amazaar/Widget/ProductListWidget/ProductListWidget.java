package com.amazaar.Widget.ProductListWidget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import com.amazaar.Module.AmazaarApplication;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Adapters.ProductListAdapter;
import com.amazaar.CommonCode.AToast;
import com.amazaar.Fragments.ProductDetailsFragment;
import com.amazaar.Handlers.CartItemHandler;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.R;
import com.amazaar.SessionManager.CustomerSession;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;
import com.amazaar.Protobuff.CustomerPbOuterClass;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class ProductListWidget extends LinearLayout implements IView<ProductListView>, View.OnClickListener {

    @Inject
    public ProductListView m_view;
    private RecyclerView rvProductList;
    private ImageView ivFilter;
    private GridLayoutManager mLayoutManager;
    @Inject
    private ProductListAdapter productListAdapter;
    private List<ProductListModel> productListModelArrayList;
    private MenuItem item;
    private FragmentActivity myContext;
    private RelativeLayout rlFilter;

    @Inject
    private CartItemHandler m_cartHandler;
    @Inject
    private CustomerSession m_customerSession;


    public ProductListWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.product_list_layout, this);
        rvProductList = (RecyclerView) findViewById(R.id.fragment_productlist_rvProductList);
        ivFilter = (ImageView) findViewById(R.id.fragment_productlist_ivFilter);
        rlFilter = (RelativeLayout) findViewById(R.id.fragment_productlist_rlFilter);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        rvProductList.setLayoutManager(mLayoutManager);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.home_category_layout, this);
        mLayoutManager = new GridLayoutManager(getContext(), 2);
        rvProductList.setLayoutManager(mLayoutManager);
        ivFilter.setOnClickListener(this);
        productListModelArrayList = new ArrayList<>();
    }


    private void initWidget() {
        getView().setProductListModelArrayList(productListModelArrayList);
        getView().setProductListAdapter(productListAdapter);
        getView().setiFilter(ivFilter);
        getView().getItemType().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                getView().getItemList(getView().getItemType().getVar());
            }
        });

        productListAdapter = new ProductListAdapter(getContext(), productListModelArrayList, getView().getMainFragment(), this);
        rvProductList.setAdapter(productListAdapter);
        productListAdapter.setOnItemClickListener(new ProductListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ProductListModel viewModel) {
                if (m_customerSession.getSession().getPrivilege() == CustomerPbOuterClass.PrivilegeTypeEnum.ADMIN){
                    ProductDetailsFragment fragmentProductDetails = new ProductDetailsFragment();
                    fragmentProductDetails.setProductListModel(viewModel);
                    getView().setProductDetailsFragment(fragmentProductDetails);
                    Utils.addNextFragment(getContext(), fragmentProductDetails, getView().getMainFragment(), false);
                }
            }
        });
        AmazaarApplication.getLoadingDialog().dismiss();
        AToast.getLoadingToast();

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public ProductListView getView() {
        return m_view;
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    public void onClick(View v) {

    }

    public void addToCart(boolean addTocart, int position) {
        if (addTocart) {
            int totalKg = productListModelArrayList.get(position).getTotalKg();
            totalKg = totalKg + 1;
            productListModelArrayList.get(position).setTotalKg(totalKg);
            m_cartHandler.handle(productListModelArrayList.get(position), position);
        } else {
            int totalKg = productListModelArrayList.get(position).getTotalKg();

            if (totalKg < 1) {
                productListModelArrayList.get(position).setTotalKg(0);
                m_cartHandler.deleteItemFromCart(position);
            } else {
                totalKg = totalKg - 1;
                productListModelArrayList.get(position).setTotalKg(totalKg);
                m_cartHandler.handle(productListModelArrayList.get(position), position);
            }

        }

        productListAdapter.notifyDataSetChanged();

    }


}
