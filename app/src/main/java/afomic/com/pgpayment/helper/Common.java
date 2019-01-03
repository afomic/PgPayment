package afomic.com.pgpayment.helper;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.StringReader;

public class Common {

    public static int getScreenWidth(Activity activity) {
        DisplayMetrics metrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(metrics);
        return metrics.widthPixels;
    }


    public static void showKeyboard(Activity activity, EditText editText) {
        // show virtual keyboard
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.showSoftInput(editText, 0);
        }
    }

    public static void hideKeyboard(Context activity, EditText editText) {
        // hide virtual keyboard
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
        if (imm != null) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        }
    }


    public static Object parseJSONToObject(String json, TypeToken objectType) {
        try {
            Gson gson = new GsonBuilder().create();
            JsonReader reader = new JsonReader(new StringReader(json));
            return gson.fromJson(reader, objectType.getType());
        } catch (JsonSyntaxException jse) {
            jse.printStackTrace();
            return null;
        }
    }


    public static String stringifyObject(Object object) {
        try {
            Gson gson = new GsonBuilder()
                    .excludeFieldsWithoutExposeAnnotation()
                    .create();
            return gson.toJson(object);
        } catch (OutOfMemoryError e) {
            return "";
        }
    }


}
