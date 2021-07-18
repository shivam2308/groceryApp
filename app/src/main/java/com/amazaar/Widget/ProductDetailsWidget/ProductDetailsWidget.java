package com.amazaar.Widget.ProductDetailsWidget;

import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.viewpager.widget.ViewPager;

import com.amazaar.Adapters.ProductPagerAdapter;
import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.ControlFlow.GetImageFromUrl;
import com.amazaar.EnumFormatter.AvailabilityStatusEnumFormatter;
import com.amazaar.EnumFormatter.ItemQuantityTypeEnumFormatter;
import com.amazaar.Fragments.EditProductDetailFragment;
import com.amazaar.Fragments.UploadImageFragment;
import com.amazaar.Interfaces.IView;
import com.amazaar.ListnerAndInputHandlers.VariableValueChange;
import com.amazaar.Protobuff.ImagePbOuterClass;
import com.amazaar.R;
import com.amazaar.Utility.Utils;
import com.google.inject.Injector;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class ProductDetailsWidget extends LinearLayout implements IView<ProductDetailsView>, View.OnClickListener {
    @Inject
    public ProductDetailsView m_view;
    private TextView m_edit;
//    private ViewPager viewPagerImages;
//    private TextView tvQuantity;
    private TextView tvName;
    private TextView tvPrice;
    private TextView tvQuantity;
    private TextView tvAvailabilityStatus;
//    private ImageView ivPlus;
//    private ImageView ivMins;
    private ImageView ivLikeUnLike;
    private ImageView itemImages;

   // private RelativeLayout rlAddTocart;
    @Inject
    public GetImageFromUrl m_getImageFromUrl;

    @Inject
    public AvailabilityStatusEnumFormatter m_availabilityStatusEnumFormatter;
    @Inject
    public ItemQuantityTypeEnumFormatter m_itemQuantityTypeEnumFormatter;

    private Bundle bundle;
    private int totalKg = 0;

    public ProductDetailsWidget(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        inflate(context, R.layout.product_deatils_layout, this);
        itemImages = findViewById(R.id.fragment_product_details_vpSlider);
//        rlAddTocart = findViewById(R.id.fragment_product_details_rlAddToCart);
//        tvQuantity = findViewById(R.id.fragment_product_details_tvTotalKg);
        tvName = findViewById(R.id.fragment_product_details_tvTitle);
        tvPrice = findViewById(R.id.fragment_product_details_tvPrice);
        tvQuantity = findViewById(R.id.fragment_product_details_tvQuantity);
        tvAvailabilityStatus = findViewById(R.id.fragment_product_details_tvAvailabilityStatus);
//        ivPlus = findViewById(R.id.fragment_product_details_ivPlus);
//        ivMins = findViewById(R.id.fragment_product_details_ivMins);
        ivLikeUnLike = findViewById(R.id.fragment_product_details_ivLikeUnLike);
        m_edit = (TextView) findViewById(R.id.editdata_btn);
        inflateLayout();
        if (!isInEditMode()) {
            injectMembers();
            initWidget();
        }
    }

    private void inflateLayout() {
        inflate(getContext(), R.layout.product_deatils_layout, this);
        //rlAddTocart.setOnClickListener(this);
//        ivPlus.setOnClickListener(this);
//        ivMins.setOnClickListener(this);
        ivLikeUnLike.setOnClickListener(this);

    }


    public void initWidget() {
        getView().getProductListModel().setListener(new VariableValueChange.ChangeListener() {
            @Override
            public void onChange() {
                tvName.setText(getView().getProductListModel().getVar().getProductName());
               // tvQuantity.setText(""+getView().getProductListModel().getVar().getPbModel().getItemName().getFirstName());
                tvPrice.setText(getView().getProductListModel().getVar().getPbModel().getPrice() + " ₹/" + m_itemQuantityTypeEnumFormatter.format(getView().getProductListModel().getVar().getPbModel().getItemQuantityType()));
                tvQuantity.setText(getView().getProductListModel().getVar().getPbModel().getQuantity() + " " + m_itemQuantityTypeEnumFormatter.format(getView().getProductListModel().getVar().getPbModel().getItemQuantityType()));
                tvAvailabilityStatus.setText(m_availabilityStatusEnumFormatter.format(getView().getProductListModel().getVar().getPbModel().getAvailabilityStatus()));
                totalKg = getView().getProductListModel().getVar().getTotalKg();
                ivLikeUnLike.setImageDrawable(getView().getProductListModel().getVar().isLike() ? getContext().getResources().getDrawable(R.drawable.ic_fav_select) : getContext().getResources().getDrawable(R.drawable.ic_fav_unselect));
                m_getImageFromUrl.setImageFromUrl(getContext(), getView().getProductListModel().getVar().getPbModel().getItemImage(), itemImages, DefaultImageUrl.ImageShowTypeEnum.ITEM);

            }
        });
        itemImages.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                UploadImageFragment uploadImageFragment = new UploadImageFragment();
                uploadImageFragment.setImageId(getView().getProductListModel().getVar().getPbModel().getDbInfo().getId());
                uploadImageFragment.setImageType(ImagePbOuterClass.ImageTypeEnum.ITEM_IMAGE);
                uploadImageFragment.setImageToBeSet(itemImages);
                getView().setUploadFragment(uploadImageFragment);
                Utils.addNextFragmentFadeAnim(uploadImageFragment, getView().getMainFragment());
            }
        });
        m_edit.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                EditProductDetailFragment editProductDetailFragment = new EditProductDetailFragment();
                editProductDetailFragment.setItemPb(getView().getProductListModel().getVar().getPbModel());
                Utils.addNextFragmentFadeAnim(editProductDetailFragment, getView().getMainFragment());
            }
        });
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
    public void onBackPressed() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
//            case R.id.fragment_product_details_ivPlus:
//                // addToCart(true);
//                break;
//            case R.id.fragment_product_details_ivMins:
//                // addToCart(false);
//                break;
            case R.id.fragment_product_details_ivLikeUnLike:
                ivLikeUnLike.setImageDrawable(getView().getProductListModel().getVar().isLike() ? getContext().getResources().getDrawable(R.drawable.ic_fav_select) : getContext().getResources().getDrawable(R.drawable.ic_fav_unselect));
                getView().getProductListModel().getVar().setLike(getView().getProductListModel().getVar().isLike() ? false : true);
                break;

//            case R.id.fragment_product_details_rlAddToCart:
//              /*  CartListFragment cartFragment = new CartListFragment();
//                cartFragment.setTargetFragment(ProductDetailsFragment.this, 222);
//                Utils.addNextFragment(getActivity(), cartFragment, ProductDetailsFragment.this, true);*/
//                break;
        }
    }

    private void addToCart(boolean addCart) {

//        if (addCart) {
//            totalKg = totalKg + 1;
//            tvQuantity.setText(totalKg + " " + getContext().getString(R.string.kg));
//
//        } else {
//            if (totalKg < 1) {
//                totalKg = totalKg - 1;
//                tvQuantity.setText(totalKg + " " + getContext().getString(R.string.kg));
//            }
//        }
    }
}
