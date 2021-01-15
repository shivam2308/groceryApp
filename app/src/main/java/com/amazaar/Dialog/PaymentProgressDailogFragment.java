package com.amazaar.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import com.amazaar.R;
import com.google.inject.Injector;
import com.kofigyan.stateprogressbar.StateProgressBar;

import java.util.ArrayList;

import javax.inject.Inject;

import roboguice.RoboGuice;

public class PaymentProgressDailogFragment extends Dialog implements View.OnClickListener {

    @Inject
    public StateProgressBar m_progressbar;

    public PaymentProgressDailogFragment(@NonNull Context context) {
        super(context);
        final Dialog dialog = new Dialog(context);
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimationTultip;
        dialog.setCanceledOnTouchOutside(false);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setGravity(Gravity.CENTER);

        dialog.setContentView(R.layout.payment_dailog);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        injectMembers();
        initializeComponent(dialog);
    }


    private void injectMembers() {
        Injector injector = RoboGuice.getInjector(getContext());
        injector.injectMembers(this);
    }

    private void initializeComponent(Dialog v) {
        m_progressbar = (StateProgressBar) v.findViewById(R.id.paymentProcess);
        ArrayList<String> list = new ArrayList<>();
        list.add("Payment");
        list.add("Order Items");
        list.add("Processing");
        list.add("Done");
        m_progressbar.setStateDescriptionData(list);
    }

    public void setState(StateProgressBar.StateNumber number) {
        m_progressbar.setCurrentStateNumber(number);
    }

    @Override
    public void onClick(View v) {

    }
}
