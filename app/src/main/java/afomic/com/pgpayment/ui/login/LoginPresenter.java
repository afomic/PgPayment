package afomic.com.pgpayment.ui.login;

import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.helper.StringUtils;
import afomic.com.pgpayment.model.User;

public class LoginPresenter {
    private LoginView mLoginView;
    private AuthManger mAuthManger;

    public LoginPresenter(LoginView view, AuthManger authManger) {
        mLoginView = view;
        mAuthManger = authManger;
        mLoginView.initView();
    }

    public void loginUser(String matricNumber, String password) {
        if (StringUtils.isEmpty(matricNumber) || StringUtils.isEmpty(password)) {
            mLoginView.notifyLoginFailed("Fields cannot be empty");
            return;
        }
        if (!StringUtils.isValidPassword(password)) {
            mLoginView.notifyLoginFailed("invalid password");
            return;
        }
        if (!StringUtils.isValidMatricNumber(matricNumber)) {
            mLoginView.notifyLoginFailed("invalid matric number");
            return;
        }
        login(matricNumber, password);
    }

    private void login(final String matricNumber, String password) {
        mLoginView.showProgress();
        mAuthManger.login(matricNumber, password, new AuthManger.AuthManagerCallback() {
            @Override
            public void onSuccess(User user) {
                mLoginView.hideProgress();
                mLoginView.showHomeView();

            }

            @Override
            public void onFailure(String reason) {
                mLoginView.hideProgress();
                mLoginView.notifyLoginFailed(reason);
            }
        });
    }

    public void handleSignUpButtonClicked() {
        mLoginView.showSignUpView();
    }

}
