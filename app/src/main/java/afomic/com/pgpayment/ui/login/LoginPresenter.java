package afomic.com.pgpayment.ui.login;

import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.helper.SharedPreferenceManager;
import afomic.com.pgpayment.helper.StringUtils;
import afomic.com.pgpayment.model.User;
import afomic.com.pgpayment.network.ApiService;
import afomic.com.pgpayment.network.param.SendSmsRequest;
import afomic.com.pgpayment.network.param.SendSmsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter {
    private LoginView mLoginView;
    private AuthManger mAuthManger;
    private SharedPreferenceManager mSharedPreferenceManager;
    private ApiService mApiService;

    public LoginPresenter(LoginView view, AuthManger authManger, SharedPreferenceManager sharedPreferenceManager, ApiService apiService) {
        mLoginView = view;
        mAuthManger = authManger;
        this.mSharedPreferenceManager = sharedPreferenceManager;
        mApiService = apiService;
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
                if (mSharedPreferenceManager.getBooleanPref(SharedPreferenceManager.PREF_USER_VERIFIED)) {
                    mLoginView.showHomeView();
                    mLoginView.hideProgress();
                } else {
                    final String otp = StringUtils.getOTP();
                    final SendSmsRequest sendSmsRequest = new SendSmsRequest();
                    sendSmsRequest.sender = "PGPayment";
                    sendSmsRequest.recipient = user.getMobileNumber();
                    sendSmsRequest.message = "Kindly verified your number \n Otp  " + otp;
                    mApiService.mPgPaymentApi.sendSms(sendSmsRequest).enqueue(new Callback<SendSmsResponse>() {
                        @Override
                        public void onResponse(Call<SendSmsResponse> call, Response<SendSmsResponse> response) {
                            mLoginView.hideProgress();
                            if (response.isSuccessful()) {
                                SendSmsResponse smsResponse = response.body();
                                if (smsResponse.code == 200) {
                                    mLoginView.showOtpVerification(otp);
                                }
                            }
                        }

                        @Override
                        public void onFailure(Call<SendSmsResponse> call, Throwable t) {
                            mLoginView.hideProgress();
                            mLoginView.notifyLoginFailed(t.getMessage());
                        }
                    });
                }


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
