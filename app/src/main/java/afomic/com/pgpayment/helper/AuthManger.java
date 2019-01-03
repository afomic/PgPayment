package afomic.com.pgpayment.helper;

import afomic.com.pgpayment.model.User;

public class AuthManger {
    private static AuthManger sAuthManger;
    private AuthManger() {

    }

    public static AuthManger getInstance() {
        if(sAuthManger==null){
             sAuthManger=new AuthManger();
        }
        return sAuthManger;
    }

    public void login(String matricNumber, String password, AuthManagerCallback callback) {
    }

    public void signUp(User user, String password, AuthManagerCallback callback) {

    }

    public interface AuthManagerCallback {
        void onSuccess(User user);

        void onFailure(String reason);
    }
}
