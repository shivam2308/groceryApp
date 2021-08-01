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

    public static void noDeliveryManFound() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("No Deliery Man Found")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.INFO)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void noModeSelected() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("No Payment Mode is Selected")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.WARNING)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void permissionGranted() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Permission Granted")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.SUCCESS)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void itemUpdated() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Item Updated")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.SUCCESS)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void getLoadingToast() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Loading..")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.INFO)
                .setDuration(Duration.LONG)
                .show();
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Loading..")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.INFO)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void noItemAvailableToast() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("No Item Available...")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.INFO)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void emptyCart() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Your Cart is Empty")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.INFO)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void updateDetails() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Details Successfully Updated")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.SUCCESS)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void plsWait() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Please Wait")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.INFO)
                .setDuration(Duration.LONG)
                .show();
    }

    public static void formFieldMissingToast() {

    }

    public static void setDeliveryMan() {
        Cue.init()
                .with(AmazaarApplication.getContext())
                .setMessage("Assign Delivery Man First")
                .setGravity(Gravity.BOTTOM)
                .setType(Type.WARNING)
                .setDuration(Duration.LONG)
                .show();
    }
}
