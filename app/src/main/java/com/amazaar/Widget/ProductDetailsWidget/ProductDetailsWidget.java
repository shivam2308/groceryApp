package com.amazaar.Widget.ProductDetailsWidget;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.amazaar.Adapters.ProductPagerAdapter;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.R;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class ProductDetailsWidget extends LinearLayout implements IView<ProductDetailsView>, View.OnClickListener {
    @Inject
    public ProductDetailsView m_view;
    private ViewPager viewPagerImages;
    private TextView tvQuantity;
    private TextView tvName;
    private TextView tvPrice;
    private ImageView ivPlus;
    private ImageView ivMins;
    private ImageView ivLikeUnLike;
    private RelativeLayout rlAddTocart;

    private ProductPagerAdapter imagePagerAdapter;

    private List<ImagePbOuterClass.ImageRefPb> pagerImgList;
    private Bundle bundle;
    private int totalKg = 0;

    public ProductDetailsWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.product_deatils_layout, this);
        viewPagerImages = findViewById(R.id.fragment_product_details_vpSlider);
        rlAddTocart = findViewById(R.id.fragment_product_details_rlAddToCart);
        tvQuantity = findViewById(R.id.fragment_product_details_tvTotalKg);
        tvName = findViewById(R.id.fragment_product_details_tvTitle);
        tvPrice = findViewById(R.id.fragment_product_details_tvPrice);
        ivPlus = findViewById(R.id.fragment_product_details_ivPlus);
        ivMins = findViewById(R.id.fragment_product_details_ivMins);
        ivLikeUnLike = findViewById(R.id.fragment_product_details_ivLikeUnLike);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.product_deatils_layout, this);
        rlAddTocart.setOnClickListener(this);
        ivPlus.setOnClickListener(this);
        ivMins.setOnClickListener(this);
        ivLikeUnLike.setOnClickListener(this);
        pagerImgList = new ArrayList<>();

    }


    private void initWidget() {
        getView().getProductListModel().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                setUpDetails();
                setUpSliderImages();
            }
        });

    }

    private void setUpDetails() {

        if (getView().getProductListModel() != null) {
            tvName.setText(getView().getProductListModel().getVar().getProductName());
            tvQuantity.setText(""+getView().getProductListModel().getVar().getPbModel().getItemName().getFirstName());
            tvPrice.setText(getView().getProductListModel().getVar().getPbModel().getPrice() + " Kg");
            totalKg = getView().getProductListModel().getVar().getTotalKg();
            ivLikeUnLike.setImageDrawable(getView().getProductListModel().getVar().isLike() ? getContext().getResources().getDrawable(R.drawable.ic_fav_select) : getContext().getResources().getDrawable(R.drawable.ic_fav_unselect));
            if(getView().getProductListModel().getVar().getPbModel()!=null){
                pagerImgList.add(getView().getProductListModel().getVar().getPbModel().getItemUrl());
            }else{
                pagerImgList.add(ImagePbOuterClass.ImageRefPb.getDefaultInstance());
            }

        }
    }

    private void setUpSliderImages() {
        try {
            imagePagerAdapter = new ProductPagerAdapter(getContext(), pagerImgList);
            viewPagerImages.setAdapter(imagePagerAdapter);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    @Override
    public ProductDetailsView getView() {
        return m_view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_product_details_ivPlus:
                // addToCart(true);
                break;
            case R.id.fragment_product_details_ivMins:
                // addToCart(false);
                break;
            case R.id.fragment_product_details_ivLikeUnLike:
                ivLikeUnLike.setImageDrawable(getView().getProductListModel().getVar().isLike() ? getContext().getResources().getDrawable(R.drawable.ic_fav_select) : getContext().getResources().getDrawable(R.drawable.ic_fav_unselect));
                getView().getProductListModel().getVar().setLike(getView().getProductListModel().getVar().isLike() ? false : true);
                break;

            case R.id.fragment_product_details_rlAddToCart:
              /*  CartListFragment cartFragment = new CartListFragment();
                cartFragment.setTargetFragment(ProductDetailsFragment.this, 222);
                Utils.addNextFragment(getActivity(), cartFragment, ProductDetailsFragment.this, true);*/
                break;
        }
    }

    private void addToCart(boolean addCart) {

        if (addCart) {
            totalKg = totalKg + 1;
            tvQuantity.setText(totalKg + " " + getContext().getString(R.string.kg));

        } else {
            if (totalKg < 1) {
                totalKg = totalKg - 1;
                tvQuantity.setText(totalKg + " " + getContext().getString(R.string.kg));
            }
        }
    }
}