package afomic.com.pgpayment.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import afomic.com.pgpayment.PGPayment;
import afomic.com.pgpayment.R;
import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.ui.main.MainActivity;
import afomic.com.pgpayment.ui.signUp.SignUpActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends AppCompatActivity implements LoginView {
    private LoginPresenter mLoginPresenter;
    @BindView(R.id.edt_matric_number)
    EditText matricNumberEditText;

    @BindView(R.id.edt_password)
    EditText passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mLoginPresenter = new LoginPresenter(this, AuthManger.getInstance());
    }


    @Override
    public void notifyLoginFailed(String reason) {
        Toast.makeText(LoginActivity.this, reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showHomeView() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void initView() {
        setTitle("Login");
    }

    @Override
    public void initListeners() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick(R.id.btn_login)
    public void loginButtonClick() {
        String matricNumber = matricNumberEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        mLoginPresenter.loginUser(matricNumber, password);
    }
    @OnClick(R.id.btn_sign_up)
    public void signUpTextClick(){
        mLoginPresenter.handleSignUpButtonClicked();
    }

    @Override
    public void showSignUpView() {
        Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
        startActivity(intent);
    }
}
