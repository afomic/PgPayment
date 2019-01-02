package afomic.com.pgpayment;

import org.junit.Before;
import org.junit.Test;

import afomic.com.pgpayment.ui.login.LoginPresenter;
import afomic.com.pgpayment.ui.login.LoginView;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

public class LoginUnitTest {
    private LoginPresenter mLoginPresenter;
    private LoginView mLoginView;

    @Before
    public void setup() {
        mLoginView = mock(LoginView.class);
        mLoginPresenter = new LoginPresenter(mLoginView);
    }

    @Test
    public void login_user_with_wrong_input() {
        mLoginPresenter.loginUser("", "");
        mLoginPresenter.loginUser("csc/2013/223332", "eee");
        mLoginPresenter.loginUser("333", "eee");
        verify(mLoginView, times(3)).notifyLoginFailed(anyString());
    }

    @Test
    public void show_progress_when_login() {
        mLoginPresenter.loginUser("csc/2013/017", "michael123");
        verify(mLoginView, times(1)).showProgress();
    }

    @Test
    public void login_user_with_right_input() {
        mLoginPresenter.loginUser("csc/2013/017", "michael123");
        verify(mLoginView, times(1)).showHomeView();
    }
}
