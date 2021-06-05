package com.amazaar.CommonCode;

import android.app.Dialog;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;


public class CustomDailogCallBack {

    Dialog m_dailog;
    private long currentMIllis = 0l;



    public Dialog getLoadingDailog() {
        final Dialog dialog = new Dialog(AmazaarApplication.getCurrentActivity());
        dialog.getWindow().getAttributes().windowAnimations = R.style.PauseDialogAnimationTultip;
        dialog.setCanceledOnTouchOutside(true);
        dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        dialog.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.setContentView(R.layout.dialog_loading);
        WindowManager.LayoutParams params = dialog.getWindow().getAttributes();
        params.width = WindowManager.LayoutParams.MATCH_PARENT;
        params.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes((WindowManager.LayoutParams) params);
        m_dailog = dialog;
        return dialog;
    }

    public void onComplete() {
        if (m_dailog.isShowing()) {
            m_dailog.dismiss();
            AmazaarApplication.getCurrentActivity().getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }

    }

}