package com.amazaar.Adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.CommonCode.DefaultImageUrl;
import com.amazaar.ControlFlow.GetImageFromUrl;
import com.amazaar.Fragments.HomeCategoryFragment;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.R;

import java.util.List;

import javax.inject.Inject;

public class HomeProductListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<ProductListModel> productModelList;
    private OnItemClickListener onItemClickListener;
    private Context mContext;
    private HomeCategoryFragment productListFragment;
    @Inject
    public GetImageFromUrl m_getImageFromUrl;

    /**
     * setup Constructure
     */
    public HomeProductListAdapter(final Context context, final List<ProductListModel> items, final HomeCategoryFragment fragment) {
        this.productModelList = items;
        this.mContext = context;
        this.productListFragment = fragment;
        m_getImageFromUrl = new GetImageFromUrl();
    }
    @Inject
    public HomeProductListAdapter(GetImageFromUrl getImageFromUrl){
        m_getImageFromUrl=getImageFromUrl;
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_home_productlist, parent, false);
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

        private ImageView ivProImg;

        public ViewHolderData(View itemView) {
            super(itemView);

            ivProImg = (ImageView) itemView.findViewById(R.id.row_home_productlist_ivProImg);

        }


        public void bindData(final ProductListModel item, final int position) {
            itemView.setTag(item);
            if(item.getPbModel()!=null) {
                m_getImageFromUrl.setImageFromUrl(mContext, item.getPbModel().getItemUrl(), ivProImg, DefaultImageUrl.ImageShowTypeEnum.ITEM);
            }else{
                ivProImg.setImageDrawable(mContext.getResources().getDrawable(item.getProductImage()));
            }
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

        }
    }
}
