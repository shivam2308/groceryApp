package com.amazaar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.amazaar.CustomeComponent.CustomTextView;
import com.amazaar.R;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.Fragments.OrderListFragment;
import com.amazaar.ListModels.OrderListModel;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<OrderListModel> orderListModels;
    private Context mContext;
    private OrderListFragment orderListFragment;



    /**
     * setup Constructure
     */

    public OrderListAdapter(final List<OrderListModel> addressListModelNew, final Context mContext, final OrderListFragment checkOutFragmnet) {
        this.orderListModels = addressListModelNew;
        this.mContext = mContext;
        this.orderListFragment = checkOutFragmnet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_order_history_list, parent, false);
        return new ViewHolderData(v);

    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderData) holder).bindData(orderListModels.get(position), position);
    }

    @Override
    public int getItemCount() {
        return orderListModels.size();
    }


    /**
     * bind ViewHolder
     */
    protected class ViewHolderData extends RecyclerView.ViewHolder {

        private CustomTextView tvOrderDate;
        private CustomTextView tvDeliveryDate;
        private CustomTextView tvOrderId;
        private CustomTextView tvCancelLable;
        private CustomTextView tvDeliveryLable;
        private RelativeLayout rlReportOrder;
        private RelativeLayout rlCancelOrder;
        private CardView cvMain;
        private ImageView ivStatus;

        public ViewHolderData(View itemView) {
            super(itemView);

            cvMain = (CardView) itemView.findViewById(R.id.row_cancel_order_cvMain);
            tvOrderDate = (CustomTextView) itemView.findViewById(R.id.row_cancel_order_tvDate);
            tvDeliveryDate = (CustomTextView) itemView.findViewById(R.id.row_cancel_order_tvDeliveryDate);
            tvOrderId = (CustomTextView) itemView.findViewById(R.id.row_cancel_order_tvOrderID);
            tvCancelLable = (CustomTextView) itemView.findViewById(R.id.row_cancel_order_tvCancelLable);
            tvDeliveryLable = (CustomTextView) itemView.findViewById(R.id.row_cancel_order_tvDeliveryLable);
            rlCancelOrder = (RelativeLayout) itemView.findViewById(R.id.row_cancel_order_rlCancelOrder);
            rlReportOrder = (RelativeLayout) itemView.findViewById(R.id.row_cancel_order_rlReportOrder);
            ivStatus = (ImageView) itemView.findViewById(R.id.row_cancel_order_ivStatus);

        }

        public void bindData(final OrderListModel item, final int position) {

            tvOrderDate.setText("" + item.getOrderDate());
            tvOrderId.setText("" + item.getOrderId());

            if (item.getStatus().equalsIgnoreCase(mContext.getString(R.string.lbl_pending))) {
                rlCancelOrder.setVisibility(View.VISIBLE);
                rlReportOrder.setVisibility(View.GONE);
                tvDeliveryLable.setText(mContext.getString(R.string.to_deliver_on));
                tvDeliveryDate.setText(item.getDeliveryDate());
                tvDeliveryDate.setVisibility(View.VISIBLE);
                tvCancelLable.setVisibility(View.GONE);
                ivStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_order_pending));
                cvMain.setCardBackgroundColor(mContext.getResources().getColor(R.color.order_pendig_bgcolro));
            } else if (item.getStatus().equalsIgnoreCase(mContext.getString(R.string.lbl_delivery))) {
                rlCancelOrder.setVisibility(View.GONE);
                rlReportOrder.setVisibility(View.VISIBLE);
                tvCancelLable.setVisibility(View.GONE);
                tvDeliveryLable.setText(mContext.getString(R.string.delivery_date));
                tvDeliveryDate.setText(item.getDeliveryDate());
                ivStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_order_delivred));
                cvMain.setCardBackgroundColor(mContext.getResources().getColor(R.color.order_delivery_bgcolro));
            } else if (item.getStatus().equalsIgnoreCase(mContext.getString(R.string.lbl_cancel))) {
                rlCancelOrder.setVisibility(View.GONE);
                rlReportOrder.setVisibility(View.VISIBLE);
                tvCancelLable.setVisibility(View.VISIBLE);
                tvDeliveryDate.setVisibility(View.GONE);
                tvDeliveryLable.setText(mContext.getString(R.string.delivery));
                ivStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_order_cancel));
                cvMain.setCardBackgroundColor(mContext.getResources().getColor(R.color.order_cancel_bgcolro));
            }


        }
    }

}
