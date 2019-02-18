package afomic.com.pgpayment.helper;

import android.os.Handler;

import com.google.gson.reflect.TypeToken;

import afomic.com.pgpayment.PGPayment;
import afomic.com.pgpayment.model.User;

public class AuthManger {
    private static AuthManger sAuthManger;
    private SharedPreferenceManager mSharedPreferenceManager;

    private AuthManger() {
        mSharedPreferenceManager = new SharedPreferenceManager(PGPayment.getContext());
    }

    public static AuthManger getInstance() {
        if (sAuthManger == null) {
            sAuthManger = new AuthManger();
        }
        return sAuthManger;
    }

    public void login(String matricNumber, String password, final AuthManagerCallback callback) {
        String userString = mSharedPreferenceManager.getStringPref(SharedPreferenceManager.PREF_USER);
        final User user = (User) Common.parseJSONToObject(userString, TypeToken.get(User.class));
        if (user != null && user.getMatricNumber().equalsIgnoreCase(matricNumber) && user.getPassword().equals(password)) {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    callback.onSuccess(user);
                }
            }, 3000);
        } else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    callback.onFailure("Invalid Matric number or password");
                }
            }, 3000);

        }

    }

    public void signUp(final User user, final AuthManagerCallback callback) {
        String userString = Common.stringifyObject(user);
        mSharedPreferenceManager.saveStringPref(SharedPreferenceManager.PREF_USER, userString);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                callback.onSuccess(user);
            }
        }, 3000);

    }

    public interface AuthManagerCallback {
        void onSuccess(User user);

        void onFailure(String reason);
    }

    public User getCurrentUser() {
        String userString = mSharedPreferenceManager.getStringPref(SharedPreferenceManager.PREF_USER);
        return (User) Common.parseJSONToObject(userString, TypeToken.get(User.class));
    }

}
