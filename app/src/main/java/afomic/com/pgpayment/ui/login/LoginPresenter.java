package afomic.com.pgpayment.ui.login;

import afomic.com.pgpayment.helper.StringUtils;

public class LoginPresenter {
    private LoginView mLoginView;

    public LoginPresenter(LoginView view) {
        mLoginView = view;
    }

    public void loginUser(String matricNumber, String password) {
        if(StringUtils.isEmpty(matricNumber)||StringUtils.isEmpty(password)){
            mLoginView.notifyLoginFailed("Invalid Matric Number or Username");
        }else {

        }
    }
}
