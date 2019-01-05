package afomic.com.pgpayment.helper;

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

    public void login(String matricNumber, String password, AuthManagerCallback callback) {
        String userString = mSharedPreferenceManager.getStringPref(SharedPreferenceManager.PREF_USER);
        User user = (User) Common.parseJSONToObject(userString, TypeToken.get(User.class));
        if (user != null && user.getMatricNumber().equalsIgnoreCase(matricNumber) && user.getPassword().equals(password)) {
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

    public User getCurrentUser() {
        String userString = mSharedPreferenceManager.getStringPref(SharedPreferenceManager.PREF_USER);
        return (User) Common.parseJSONToObject(userString, TypeToken.get(User.class));
    }

}
