package com.amazaar.Adapters;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;


import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.ControlFlow.GetImageFromUrl;
import com.amazaar.CustomeComponent.CustomTextView;
import com.amazaar.Fragments.CartListFragment;
import com.amazaar.ListModels.CartListModel;
import com.amazaar.R;
import com.amazaar.Widget.CartWidget.CartWidget;

import java.util.List;

import javax.inject.Inject;


/**
 * *************************************************************************
 *
 * @ClassdName:CartListAdapter
 * @CreatedDate:
 * @ModifiedBy: not yet
 * @ModifiedDate: not yet
 * @purpose:This Class use for cart list adapter to display cart items & user can add and remove item.
 * <p/>
 * *************************************************************************
 */



public class CartListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<CartListModel> productModelList;
    private Context mContext;
    private CartWidget orderListFragmentNew;
    @Inject
    public GetImageFromUrl m_getImageFromUrl;

    /**
     * setup Constructure
     */


    public CartListAdapter(final Context context, final List<CartListModel> items, final CartWidget fragment) {
        this.productModelList = items;
        this.mContext = context;
        this.orderListFragmentNew = fragment;
        m_getImageFromUrl = new GetImageFromUrl();
    }

    @Inject
    public CartListAdapter(){

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_cart_list, parent, false);
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

    /**
     * bind ViewHolder
     */


    protected class ViewHolderData extends RecyclerView.ViewHolder {

        private CustomTextView tvProductName;
        private CustomTextView tvProductPrice;
        private CustomTextView tvKg;
        private CustomTextView tvTotalKg;
        private ImageView ivProImg;
        private ImageView ivPlus;
        private ImageView ivMins;
        private ImageView ivDelete;


        public ViewHolderData(View itemView) {
            super(itemView);

            tvProductName = (CustomTextView) itemView.findViewById(R.id.row_cartlist_tvName);
            tvProductPrice = (CustomTextView) itemView.findViewById(R.id.row_cartlist_tvPrice);
            tvTotalKg = (CustomTextView) itemView.findViewById(R.id.row_cartlist_tvTotalKg);
            tvKg = (CustomTextView) itemView.findViewById(R.id.row_cartlist_tvKg);
            ivProImg = (ImageView) itemView.findViewById(R.id.row_cartlist_ivProImg);
            ivPlus = (ImageView) itemView.findViewById(R.id.row_cartlist_ivPlus);
            ivMins = (ImageView) itemView.findViewById(R.id.row_cartlist_ivMins);
            ivDelete = (ImageView) itemView.findViewById(R.id.row_cartlist_ivDelete);


        }


        public void bindData(final CartListModel item, final int position)
        {

            tvProductName.setText("" + item.getProductName());
            tvProductPrice.setText("₹"+item.getProductPrice());
            tvKg.setText(item.getKg());
            tvTotalKg.setText(""+item.getProductQuantity());
            itemView.setTag(item);
            //ivProImg.setImageDrawable(mContext.getResources().getDrawable(item.getProductImages()));
            m_getImageFromUrl.setImageFromUrl(mContext, item.getOnitemChange().getData().getItem().getItemUrl(), ivProImg, DefaultImageUrl.ImageShowTypeEnum.ITEM);


            ivPlus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   orderListFragmentNew.addToCart(true, position);
                }
            });


            ivMins.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   orderListFragmentNew.addToCart(false, position);
                }
            });

            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                   //orderListFragmentNew.deleteItem(position);
                }
            });

        }
    }
}
