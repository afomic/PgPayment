package afomic.com.pgpayment.helper;

import afomic.com.pgpayment.model.User;

public class AuthManger {
    public static void login(String matricNumber, String password, AuthManagerCallback callback) {
        callback.onSuccess(null);
    }

    public static void signUp(User user, String password, AuthManagerCallback callback) {

    }

    public interface AuthManagerCallback {
        void onSuccess(User user);

        void onFailure(String reason);
    }
}
