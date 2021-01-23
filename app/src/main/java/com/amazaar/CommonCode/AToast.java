package com.amazaar.CommonCode;

import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;

import com.amazaar.Module.AmazaarApplication;
import com.fxn.cue.Cue;
import com.fxn.cue.enums.Duration;
import com.fxn.cue.enums.Type;

public class AToast {

    public static void normalToast() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("This is a sample Toast - Check it out")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.DANGER)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void uploadToast() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Uploaded")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.SUCCESS)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void failedToast() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Failed")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.DANGER)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void customToast() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("This is a sample Toast - Check it out")
                .setGravity(Gravity.CENTER_VERTICAL | Gravity.BOTTOM)
                .setType(Type.CUSTOM)
                .setDuration(Duration.LONG)
                .setBorderWidth(5)
                .setCornerRadius(10)
                .setCustomFontColor(Color.parseColor("#fd79a8"),
                        Color.parseColor("#ffffff"),
                        Color.parseColor("#e84393"))
                //.setFontFace("fonts/custom_font.ttf")
                .setPadding(30)
                .setTextSize(20)
                .show();
    }

    public static void pleaseSelect() {
        Log.e("Toast", "hai");
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Please Select Feedback Or Complaint")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.WARNING)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void notSendEmptyMessage() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Please Enter Some Message !!!")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.INFO)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void userCancelPayment() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Payment Cancelled")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.INFO)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void userPaymentFailed() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Payment Failed")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.INFO)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void orderDeliverdFailed() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Somting Went Wormg Please try Again Later!!")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.DANGER)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void orderDeliveredSuccess() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Order Deliverd Success")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.PRIMARY)
                .setDuration(Duration.LONG)
                .show();
    }
}
