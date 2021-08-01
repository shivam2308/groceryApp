package com.amazaar.Dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.amazaar.ClientServices.CloseAndOutForDeliveryClientService;
import com.amazaar.ControlFlow.OutForDelivery;
import com.amazaar.Protobuff.BuyPbOuterClass;
import com.google.inject.Injector;

import java.util.concurrent.ExecutionException;

import javax.annotation.Nullable;
import javax.inject.Inject;

import roboguice.RoboGuice;


public class OutForDeliveryDailogFragment extends DialogFragment {

    @Inject
    private OutForDelivery m_outForDelivery;

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        injectMembers();
        return new AlertDialog.Builder(getActivity()).setTitle("Alert")
                .setMessage("This Order Going to Out for delivery")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        m_outForDelivery.outForDelivery(getArguments().get("parentOrderId").toString());
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).create();
    }

    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }
}
