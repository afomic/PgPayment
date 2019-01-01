package afomic.com.pgpayment;

import org.junit.BeforeClass;
import org.junit.Test;

import afomic.com.pgpayment.ui.login.LoginPresenter;
import afomic.com.pgpayment.ui.login.LoginView;

import static org.mockito.Mockito.*;

public class LoginUnitTest {
    private static LoginPresenter mLoginPresenter;
    private static LoginView mLoginView;

    @BeforeClass
    public static void setup() {
        mLoginView = mock(LoginView.class);
        mLoginPresenter = new LoginPresenter(mLoginView);
    }

    @Test
    public void login_user_with_wrong_input() {
        mLoginPresenter.loginUser("","");
        mLoginPresenter.loginUser("csc/2013/223332","eee");
        mLoginPresenter.loginUser("333","eee");
        verify(mLoginView,times(2)).notifyLoginFailed("Invalid Matric Number or Username");
    }
    @Test
    public void show_progress_when_login() {
        mLoginPresenter.loginUser("","");
        verify(mLoginView,times(1)).showProgress();
    }
    @Test
    public void login_user_with_right_input() {
        mLoginPresenter.loginUser("csc/2013/017","michael123");
        verify(mLoginView,times(1)).showHomeView();
    }
}
