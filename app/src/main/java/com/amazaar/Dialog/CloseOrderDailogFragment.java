package com.amazaar.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.amazaar.ClientServices.CloseAndOutForDeliveryClientService;
import com.amazaar.Protobuff.BuyPbOuterClass;

import java.util.concurrent.ExecutionException;

import javax.annotation.Nullable;


public class CloseOrderDailogFragment extends DialogFragment {

    private CloseAndOutForDeliveryClientService m_CloseAndOutForDeliveryClientService;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        m_CloseAndOutForDeliveryClientService = new CloseAndOutForDeliveryClientService();
        return new AlertDialog.Builder(getActivity()).setTitle("Alert")
                .setMessage("Are you Want to close this Order.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        try {
                            m_CloseAndOutForDeliveryClientService.get(getOrderId());
                        } catch (ExecutionException e) {
                            e.printStackTrace();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).create();
    }

    private String getOrderId() {
        return getArguments().get("parentOrderId").toString() + "#" + BuyPbOuterClass.DeliveryStatusEnum.CLOSED.name();
    }
}
