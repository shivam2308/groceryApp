package com.amazaar.Utility;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.widget.EditText;
import android.widget.Toast;

import com.amazaar.Module.AmazaarApplication;

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
}
