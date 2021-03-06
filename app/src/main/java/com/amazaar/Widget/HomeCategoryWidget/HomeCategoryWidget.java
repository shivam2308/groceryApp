package com.amazaar.Widget.HomeCategoryWidget;

import android.app.FragmentManager;
import android.content.Context;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Adapters.HomeProductListAdapter;
import com.amazaar.Adapters.ShopAdapter;
import com.amazaar.CommonCode.TempListData;
import com.amazaar.ControlFlow.GetImageFromUrl;
import com.amazaar.Fragments.MenuFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;
import com.yarolegovich.discretescrollview.DSVOrientation;
import com.yarolegovich.discretescrollview.DiscreteScrollView;
import com.yarolegovich.discretescrollview.InfiniteScrollAdapter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import roboguice.RoboGuice;

public class HomeCategoryWidget extends LinearLayout implements IView<HomeCategoryView>, View.OnClickListener{

    @Inject
    public HomeCategoryView m_view;
    private RecyclerView rvProductList;
    private GridLayoutManager mLayoutManager;
    @Inject
    private HomeProductListAdapter productListAdapter;
    private InfiniteScrollAdapter<ShopAdapter.ViewHolder> shopAdapter;
    private List<ProductListModel> productListModelArrayList;
    private List<Integer> pagerImgList;
    private DiscreteScrollView discreteScrollView;
    private TextView tvTopSaller;
    private TextView tvAll;
    private TextView tvPopular;
    private TextView tvWhatsNew;

    public HomeCategoryWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.home_category_layout, this);
        rvProductList = (RecyclerView) findViewById(R.id.fragment_home_rvProductList);
        discreteScrollView=(DiscreteScrollView) findViewById(R.id.product_picker);
        tvAll=(TextView)  findViewById(R.id.fragment_home_tvAll);
        tvPopular=(TextView) findViewById(R.id.fragment_home_tvPopular);
        tvTopSaller=(TextView)  findViewById(R.id.fragment_home_tvTopSaller);
        tvWhatsNew=(TextView) findViewById(R.id.fragment_home_tvWhatsNew);
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

        tvAll.setOnClickListener(this);
        tvPopular.setOnClickListener(this);
        tvTopSaller.setOnClickListener(this);
        tvWhatsNew.setOnClickListener(this);
    }


    private void initWidget() {
        setUpSliderImages();
        getListData();
        productListAdapter.setOnItemClickListener(new HomeProductListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, ProductListModel viewModel) {
               /* ProductListFragment fragmentProductDetails = new ProductListFragment();
                Utils.addNextFragment(getActivity(), fragmentProductDetails, HomeCategoryFragment.this, false);*/
            }
        });

    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public HomeCategoryView getView() {
        return m_view;
    }


    private void getListData() {

        TempListData tempListData = new TempListData();
        productListModelArrayList = tempListData.getHomeProductList();
        productListAdapter = new HomeProductListAdapter(getContext(), productListModelArrayList, getView().getMainFragment());
        rvProductList.setAdapter(productListAdapter);
        rvProductList.setNestedScrollingEnabled(false);
    }

    /**
     * SetUp slider images
     */

    private void setUpSliderImages() {
        try {
            pagerImgList = new ArrayList<>();
            pagerImgList.add(R.drawable.slider_one);
            pagerImgList.add(R.drawable.slider_two);
            pagerImgList.add(R.drawable.slider_three);
            pagerImgList.add(R.drawable.slider_four);
            pagerImgList.add(R.drawable.slider_five);
            pagerImgList.add(R.drawable.slider_siz);
            discreteScrollView.setOrientation(DSVOrientation.HORIZONTAL);


            shopAdapter = InfiniteScrollAdapter.wrap(new ShopAdapter( pagerImgList,getContext()));
            discreteScrollView.setAdapter(shopAdapter);


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onClick(View v) {

       /* if(v==tvAll)
        {
            ProductListFragment fragmentProductDetails = new ProductListFragment();
            Utils.addNextFragment(getActivity(), fragmentProductDetails, HomeCategoryFragment.this, false);
        }
        else if(v==tvPopular)
        {
            ProductListFragment fragmentProductDetails = new ProductListFragment();
            Utils.addNextFragment(getActivity(), fragmentProductDetails, HomeCategoryFragment.this, false);
        }
        else if(v==tvTopSaller)
        {
            ProductListFragment fragmentProductDetails = new ProductListFragment();
            Utils.addNextFragment(getActivity(), fragmentProductDetails, HomeCategoryFragment.this, false);
        }
        else if(v==tvWhatsNew)
        {
            ProductListFragment fragmentProductDetails = new ProductListFragment();
            Utils.addNextFragment(getActivity(), fragmentProductDetails, HomeCategoryFragment.this, false);
        }*/
    }

}
