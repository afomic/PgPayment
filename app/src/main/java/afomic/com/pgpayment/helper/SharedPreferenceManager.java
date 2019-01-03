package afomic.com.pgpayment.helper;

import android.content.Context;
import android.content.SharedPreferences;


public class SharedPreferenceManager {
    private SharedPreferences mSharedPreferences;

    private static final String PREFERENCE_FILE_NAME = "afomic.com.pgpayment";
    public static final String PREF_USER = "user";
    public static final String PREF_MUTE_NOTIFICATION = "mute_notification";
    public static final String PREF_USERS = "users";
    public static final String PREF_NOTE = "note";

    public SharedPreferenceManager(Context context) {
        mSharedPreferences = context.getSharedPreferences(PREFERENCE_FILE_NAME, Context.MODE_PRIVATE);
    }

    public void saveStringPref(String key, String value) {
        mSharedPreferences.edit()
                .putString(key, value)
                .apply();
    }

    public String getStringPref(String key) {
        return mSharedPreferences.getString(key, null);
    }

    public void saveBooleanPref(String key, boolean value) {
        mSharedPreferences.edit()
                .putBoolean(key, value)
                .apply();
    }

    public Boolean getBooleanPref(String key) {
        return mSharedPreferences.getBoolean(key, false);
    }

    public void saveIntegerPref(String key, int value) {
        mSharedPreferences.edit()
                .putInt(key, value)
                .apply();
    }

    public int getIntegerPref(String key) {
        return mSharedPreferences.getInt(key, 0);
    }
}
