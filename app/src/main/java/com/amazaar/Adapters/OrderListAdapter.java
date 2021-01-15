package com.amazaar.Adapters;

import android.content.Context;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.CustomeComponent.CustomTextView;
import com.amazaar.ListModels.OrderListModel;
import com.amazaar.ListModels.ProductListModel;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.amazaar.R;

import java.util.List;

public class OrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements View.OnClickListener {

    private List<OrderListModel> orderListModels;
    private Context mContext;
    private OnItemClickListener onItemClickListener;


    /**
     * setup Constructure
     */

    public OrderListAdapter(final List<OrderListModel> addressListModelNew, final Context mContext) {
        this.orderListModels = addressListModelNew;
        this.mContext = mContext;
    }
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
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

    @Override
    public void onClick(View v) {
        if (onItemClickListener != null) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    onItemClickListener.onItemClick(v, (OrderListModel) v.getTag());
                }
            }, 200);
        }
    }

    public interface OnItemClickListener {
        void onItemClick(View view, OrderListModel viewModel);
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
                itemView.setTag(item);
                tvOrderDate.setText("" + item.getOrderDate());
                tvOrderId.setText("" + item.getOrderId());

                if (item.getStatus() == BuyPbOuterClass.DeliveryStatusEnum.PENDING) {
                    rlCancelOrder.setVisibility(View.VISIBLE);
                    rlReportOrder.setVisibility(View.GONE);
                    tvDeliveryLable.setText(mContext.getString(R.string.to_deliver_on));
                    tvDeliveryDate.setText(item.getDeliveryDate());
                    tvDeliveryDate.setVisibility(View.VISIBLE);
                    tvCancelLable.setVisibility(View.GONE);
                    ivStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_order_pending));
                    cvMain.setCardBackgroundColor(mContext.getResources().getColor(R.color.order_pendig_bgcolro));
                } else if (item.getStatus() == BuyPbOuterClass.DeliveryStatusEnum.DELIVERED) {
                    rlCancelOrder.setVisibility(View.GONE);
                    rlReportOrder.setVisibility(View.VISIBLE);
                    tvCancelLable.setVisibility(View.GONE);
                    tvDeliveryLable.setText(mContext.getString(R.string.delivery_date));
                    tvDeliveryDate.setText(item.getDeliveryDate());
                    ivStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_order_delivred));
                    cvMain.setCardBackgroundColor(mContext.getResources().getColor(R.color.order_delivery_bgcolro));
                } else if (item.getStatus() == BuyPbOuterClass.DeliveryStatusEnum.CLOSED) {
                    rlCancelOrder.setVisibility(View.GONE);
                    rlReportOrder.setVisibility(View.VISIBLE);
                    tvCancelLable.setVisibility(View.VISIBLE);
                    tvDeliveryDate.setVisibility(View.GONE);
                    tvDeliveryLable.setText(mContext.getString(R.string.delivery));
                    ivStatus.setImageDrawable(mContext.getResources().getDrawable(R.drawable.ic_order_cancel));
                    cvMain.setCardBackgroundColor(mContext.getResources().getColor(R.color.order_cancel_bgcolro));
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
