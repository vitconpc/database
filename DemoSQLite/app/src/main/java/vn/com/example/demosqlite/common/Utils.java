package vn.com.example.demosqlite.common;

import android.content.Context;
import android.content.SharedPreferences;

public class Utils {
    public static void saveInt(Context context, String key, int value) {
        SharedPreferences preferences = context.getSharedPreferences(key, context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(Constants.SHARE_PREFERENCE, context.MODE_PRIVATE);
        return preferences.getInt(key, 100);
    }
}
