package com.amazaar.CommonCode;

import android.graphics.Color;
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
}
