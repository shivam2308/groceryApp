package com.amazaar.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.amazaar.CustomeComponent.CustomTextView;
import com.amazaar.ListModels.AddressListModelNew;
import com.amazaar.R;

import java.util.List;

/**
 * Created by Kailash on 8/6/2018.
 */

public class AddressListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<AddressListModelNew> addressListModelNew;
    private Context mContext;
   // private CheckOutFragmnet checkOutFragmnet;

    /**
     * setup Constructure
     */

    public AddressListAdapter(final List<AddressListModelNew> addressListModelNew, final Context mContext) {
        this.addressListModelNew = addressListModelNew;
        this.mContext = mContext;
        //this.checkOutFragmnet = checkOutFragmnet;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_address_list, parent, false);
        return new ViewHolderData(v);

    }
    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ViewHolderData) holder).bindData(addressListModelNew.get(position), position);
    }

    @Override
    public int getItemCount() {
        return addressListModelNew.size();
    }

    /**
     * bind ViewHolder
     */

    protected class ViewHolderData extends RecyclerView.ViewHolder {

        private CustomTextView tvAddress;

        public ViewHolderData(View itemView) {
            super(itemView);

            tvAddress= (CustomTextView) itemView.findViewById(R.id.row_addresslistnew_tvAddress);

        }

        public void bindData(final AddressListModelNew item, final int position)
        {

            tvAddress.setText(""+item.getAddress());

        }
    }


}
