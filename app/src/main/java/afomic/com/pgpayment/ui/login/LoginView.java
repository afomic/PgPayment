package afomic.com.pgpayment.ui.login;

import afomic.com.pgpayment.ui.base.BaseView;

public interface LoginView  extends BaseView {
    void notifyLoginFailed(String reason);
    void showHomeView();
}
