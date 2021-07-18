package com.amazaar.Utility;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

import com.amazaar.CommonCode.AToast;
import com.amazaar.Module.AmazaarApplication;
import com.amazaar.R;
import com.prod.basic.common.code.Strings;

import java.util.List;

public class AndroidUtility {

    public static String getTextFromEditText(EditText editText) {
        return editText.getText().toString().toLowerCase().trim();
    }

    public static void startActivity(Context context, Class clazz) {
        context.startActivity(new Intent(context, clazz));
    }

    public static void getMakeTextToastLong(Context context, String text) {
        Toast.makeText(context, "text", Toast.LENGTH_LONG).show();
    }

    public static void getMakeTextToastShort(Context context, String text) {
        Toast.makeText(context, "text", Toast.LENGTH_SHORT).show();
    }

    public static boolean isPackageExisted(String targetPackage) {
        List<ApplicationInfo> packages;
        PackageManager pm;

        pm = AmazaarApplication.getCurrentActivity().getPackageManager();
        packages = pm.getInstalledApplications(0);
        for (ApplicationInfo packageInfo : packages) {
            if (packageInfo.packageName.equals(targetPackage)) {
                return true;
            }
        }
        return false;
    }

    public static Dialog getLoadingDailog(){
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
        return dialog;
    }
    public static void goToHome(){
        int f = AmazaarApplication.getFragmentManager().getBackStackEntryCount();
        while (f>=1){
            AmazaarApplication.getFragmentManager().popBackStack();
            f=f-1;
        }
    }
}
