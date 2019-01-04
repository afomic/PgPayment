package afomic.com.pgpayment.helper;

import android.content.Context;

import com.google.gson.reflect.TypeToken;

import afomic.com.pgpayment.model.User;

public class AuthManger {
    private static AuthManger sAuthManger;
    private SharedPreferenceManager mSharedPreferenceManager;

    private AuthManger(Context context) {
        mSharedPreferenceManager = new SharedPreferenceManager(context);
    }

    public static AuthManger getInstance(Context context) {
        if (sAuthManger == null) {
            sAuthManger = new AuthManger(context);
        }
        return sAuthManger;
    }

    public void login(String matricNumber, String password, AuthManagerCallback callback) {
        String userString = mSharedPreferenceManager.getStringPref(SharedPreferenceManager.PREF_USER);
        User user = (User) Common.parseJSONToObject(userString, TypeToken.get(User.class));
        if (user != null && user.getMatricNumber().equals(matricNumber) && user.getPassword().equals(password)) {
            callback.onSuccess(user);
        } else {
            callback.onFailure("Invalid Matric number or passsword");
        }

    }

    public void signUp(User user, AuthManagerCallback callback) {
        String userString = Common.stringifyObject(user);
        mSharedPreferenceManager.saveStringPref(SharedPreferenceManager.PREF_USER, userString);
        callback.onSuccess(user);
    }

    public interface AuthManagerCallback {
        void onSuccess(User user);

        void onFailure(String reason);
    }
}
