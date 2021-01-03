package com.amazaar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.CustomeComponent.CustomTextView;
import com.amazaar.ListModels.OrderSummaryListModel;
import com.amazaar.R;

import java.util.List;

public class OrderSummaryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<OrderSummaryListModel> orderListModelNewList;
    private Context mContext;
    // private CheckOutFragmnet checkOutFragmnet;


    /**
     * setup Constructure
     */
    public OrderSummaryListAdapter(List<OrderSummaryListModel> orderListModelNewList, Context mContext) {
        this.orderListModelNewList = orderListModelNewList;
        this.mContext = mContext;
        // this.checkOutFragmnet = checkOutFragmnet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.order_summary_list, parent, false);
        return new ViewHolderData(v);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderData) holder).bindData(orderListModelNewList.get(position), position);
    }

    @Override
    public int getItemCount() {
        return orderListModelNewList.size();
    }


    /**
     * bind ViewHolder
     */
    protected class ViewHolderData extends RecyclerView.ViewHolder {

        private CustomTextView tvOrederId;
        private CustomTextView tvOrederName;
        private CustomTextView tvOrderQuntity;
        private CustomTextView tvOrderPrice;

        public ViewHolderData(View itemView) {
            super(itemView);
            tvOrederId = (CustomTextView) itemView.findViewById(R.id.row_orderlist_new_tvOrederid);
            tvOrederName = (CustomTextView) itemView.findViewById(R.id.row_orderlist_new_tvOrederName);
            tvOrderQuntity = (CustomTextView) itemView.findViewById(R.id.row_orderlist_new_tvQuntity);
            tvOrderPrice = (CustomTextView) itemView.findViewById(R.id.row_orderlist_new_tvPrice);

        }

        public void bindData(final OrderSummaryListModel item, final int position) {

            tvOrederId.setText("" + item.getOrderId());
            tvOrederName.setText("" + item.getOrderName());
            tvOrderQuntity.setText("" + item.getQuntity());
            tvOrderPrice.setText("" + item.getPrice());


        }
    }
}
