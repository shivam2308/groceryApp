package com.amazaar.Utility;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.Toast;

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
}
