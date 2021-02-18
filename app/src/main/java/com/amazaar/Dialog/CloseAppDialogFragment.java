package com.amazaar.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.amazaar.ClientServices.CloseAndOutForDeliveryClientService;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.Protobuff.BuyPbOuterClass;

import java.util.concurrent.ExecutionException;

import javax.annotation.Nullable;

public class CloseAppDialogFragment extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return new AlertDialog.Builder(getActivity()).setTitle("Alert")
                .setMessage("Are you Want to Exit.")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        AmazaarApplication.getCurrentActivity().finishAffinity();
                    }
                }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dismiss();
                    }
                }).create();
    }

}
