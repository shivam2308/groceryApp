package com.amazaar.Adapters;

import android.content.Context;
import android.os.Handler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.ControlFlow.GetImageFromUrl;
import com.amazaar.CustomeComponent.CustomTextView;
import com.amazaar.Fragments.ProductListFragment;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.R;
import com.amazaar.Widget.ProductListWidget.ProductListWidget;

import java.util.List;

import javax.inject.Inject;


public class ProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<ProductListModel> productModelList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private ProductListFragment productListFragment;
    private ProductListWidget productListWidget;
    @Inject
    public GetImageFromUrl m_getImageFromUrl;


    public ProductListAdapter(final Context context, final List<ProductListModel> items, final ProductListFragment fragment,final  ProductListWidget productListWidget) {
        this.productModelList = items;
        this.mContext = context;
        this.productListFragment = fragment;
        this.productListWidget = productListWidget;
        m_getImageFromUrl= new GetImageFromUrl();
    }

    @Inject
    public ProductListAdapter(){

    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_product_list, parent, false);
        v.setOnClickListener(this);
        return new ViewHolderData(v);

    }


    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderData) holder).bindData(productModelList.get(position), position);
    }


    @Override
    public int getItemCount() {
        return productModelList.size();
    }

    @Override
    public void onClick(final View v) {
        // Give some time to the ripple to finish the effect
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (ProductListModel) v.getTag());
                }
            }, 200);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, ProductListModel viewModel);
    }

    /**
     * bind ViewHolder
     */

    protected class ViewHolderData extends RecyclerView.ViewHolder {

        private CustomTextView tvProductName;
        private CustomTextView tvProductPrice;
        private CustomTextView tvAddToCard;
        private CustomTextView tvTotalKg;
        private ImageView ivProImg;
        private ImageView ivPlus;
        private ImageView ivMins;
        private ImageView ivLikeUnLike;
        private RelativeLayout rlTotalCartItem;



        public ViewHolderData(View itemView) {
            super(itemView);

            tvProductName = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvName);
            tvProductPrice = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvPrice);
            tvAddToCard = (CustomTextView) itemView.findViewById(R.id.row_produclist_tvAddtoCart);
            tvTotalKg = (CustomTextView) itemView.findViewById(R.id.row_productlist_tvTotalKg);
            ivLikeUnLike = (ImageView) itemView.findViewById(R.id.row_productlist_ivLikeUnLike);
            ivProImg = (ImageView) itemView.findViewById(R.id.row_productlist_ivProImg);
            ivPlus = (ImageView) itemView.findViewById(R.id.row_productlist_ivPlus);
            ivMins = (ImageView) itemView.findViewById(R.id.row_productlist_ivMins);
            rlTotalCartItem = (RelativeLayout) itemView.findViewById(R.id.row_produclist_rlTotalCart);


        }


        /**
         * bind data
         */
        public void bindData(final ProductListModel item, final int position) {

            tvProductName.setText("" + item.getProductName());
            tvProductPrice.setText(item.getProductPrice());
            tvTotalKg.setText(""+item.getTotalKg());
            itemView.setTag(item);
            m_getImageFromUrl.setImageFromUrl(mContext, item.getPbModel().getItemUrl(), ivProImg, DefaultImageUrl.ImageShowTypeEnum.ITEM);
          //  ivProImg.setImageDrawable(mContext.getResources().getDrawable(item.getProductImage()));
            ivLikeUnLike.setImageDrawable(item.isLike()? mContext.getResources().getDrawable(R.drawable.ic_fav_select) : mContext.getResources().getDrawable(R.drawable.ic_fav_unselect));
            rlTotalCartItem.setVisibility(item.getTotalKg() == 0 ? View.GONE : View.VISIBLE);
            tvAddToCard.setVisibility(item.getTotalKg() == 0 ? View.VISIBLE : View.GONE);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(final View v) {

                    // Give some time to the ripple to finish the effect
                    if (onItemClickListener != null) {
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                onItemClickListener.onItemClick(v, item);
                            }
                        }, 200);
                    }
                }
            });


            /**
             * add item quantity
             */


            ivPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    productListWidget.addToCart(true, position);
                }
            });

            /**
             * remove item quantity
             */


            ivMins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    productListWidget.addToCart(false, position);
                }
            });

            /**
             * add item on cart
             */


            tvAddToCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    productListWidget.addToCart(true, position);
                }
            });

            /**
             * like and unlike product
             */

            ivLikeUnLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    //productListFragment.likeUnLike(position);
                }
            });

        }
    }
}
