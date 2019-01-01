package afomic.com.pgpayment.ui.login;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import afomic.com.pgpayment.R;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter mLoginPresenter;

    private EditText matricNumberEditText;
    private EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mLoginPresenter = new LoginPresenter(this);
    }


    @Override
    public void notifyLoginFailed(String reason) {

    }

    @Override
    public void showHomeView() {

    }

    @Override
    public void intView(View view) {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void showProgress() {

    }
}
