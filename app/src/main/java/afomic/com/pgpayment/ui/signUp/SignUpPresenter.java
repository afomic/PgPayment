package afomic.com.pgpayment.ui.signUp;

import android.content.Context;

import afomic.com.pgpayment.R;
import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.helper.FacultyHelper;
import afomic.com.pgpayment.helper.StringUtils;
import afomic.com.pgpayment.model.User;
import afomic.com.pgpayment.network.ApiService;
import afomic.com.pgpayment.network.param.SendEmailRequest;
import afomic.com.pgpayment.network.param.SendEmailResponse;
import afomic.com.pgpayment.network.param.SendSmsRequest;
import afomic.com.pgpayment.network.param.SendSmsResponse;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SignUpPresenter {
    private AuthManger mAuthManger;
    private SignUpView mSignUpView;
    private String[] departmentList = {};
    private ApiService mApiService;
    private Context mContext;

    public SignUpPresenter(SignUpView signUpView, AuthManger authManger, Context context) {
        mSignUpView = signUpView;
        mAuthManger = authManger;
        mContext = context;
        mApiService = ApiService.getInstance(context);
        signUpView.initView();
        signUpView.initListeners();

    }

    public void signUpUser(User user) {
        if (!StringUtils.isValidMatricNumber(user.getMatricNumber())) {
            mSignUpView.notifySignUpFail("Please enter valid matric number");
            return;
        }
        if (!StringUtils.isEmailValid(user.getEmail())) {
            mSignUpView.notifySignUpFail("Please a valid email");
            return;
        }
        if (StringUtils.isEmpty(user.getFaculty())) {
            mSignUpView.notifySignUpFail("Please select a faculty");
            return;
        }
        if (StringUtils.isEmpty(user.getDepartment())) {
            mSignUpView.notifySignUpFail("Please select a department");
            return;
        }
        if (StringUtils.isEmpty(user.getFirstName())) {
            mSignUpView.notifySignUpFail("Please provide a first name");
            return;
        }
        if (StringUtils.isEmpty(user.getLastName())) {
            mSignUpView.notifySignUpFail("Please provide a last name");
            return;
        }
        if (!StringUtils.isValidPassword(user.getPassword())) {
            mSignUpView.notifySignUpFail("Please provide a valid password");
            return;
        }
        if (StringUtils.isEmpty(user.getMobileNumber()) || user.getMobileNumber().length() != 13) {
            mSignUpView.notifySignUpFail("Please provide a valid phone number");
            return;
        }
        signUp(user);
    }

    private void signUp(User user) {
        mSignUpView.showProgress();
        mAuthManger.signUp(user, new AuthManger.AuthManagerCallback() {
            @Override
            public void onSuccess(User user) {
                final String otp = StringUtils.getOTP();
                final SendSmsRequest sendSmsRequest = new SendSmsRequest();
                sendSmsRequest.sender = "PGPayment";
                sendSmsRequest.recipient = "+" + user.getMobileNumber();
                sendSmsRequest.message = "Kindly verified your number \n Otp  " + otp;
                mApiService.mPgPaymentApi.sendSms(sendSmsRequest).enqueue(new Callback<SendSmsResponse>() {
                    @Override
                    public void onResponse(Call<SendSmsResponse> call, Response<SendSmsResponse> response) {
                        mSignUpView.hideProgress();
                        if (response.isSuccessful()) {
                            SendSmsResponse smsResponse = response.body();
                            if (smsResponse.code == 200) {
                                mSignUpView.showOtpVerification(otp);

                            } else {
                                mSignUpView.notifySignUpFail("Sign up failed try again");
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SendSmsResponse> call, Throwable t) {
                        mSignUpView.hideProgress();
                        mSignUpView.notifySignUpFail(t.getMessage());
                    }
                });
                SendEmailRequest request = new SendEmailRequest();
                request.from = "PGPayment";
                request.to = user.getEmail();
                request.subject = "Welcome to PGPayment";
                request.html = String.format(mContext.getString(R.string.welcome_email_content), user.getFirstName());
                mApiService.mPgPaymentApi.sendMail(request).enqueue(new Callback<SendEmailResponse>() {
                    @Override
                    public void onResponse(Call<SendEmailResponse> call, Response<SendEmailResponse> response) {

                    }

                    @Override
                    public void onFailure(Call<SendEmailResponse> call, Throwable t) {
                        mSignUpView.notifySignUpFail(t.getMessage());
                    }
                });

            }

            @Override
            public void onFailure(String reason) {
                mSignUpView.hideProgress();
                mSignUpView.notifySignUpFail(reason);
            }
        });
    }

    public void populateDepartment(String selectedFaculty) {
        departmentList = FacultyHelper.getDepartments(selectedFaculty);
        mSignUpView.showFacultyDepartments(departmentList);
    }

    public String getDepartmentName(int selectedDepartment) {
        if (departmentList.length > 0) {
            return departmentList[selectedDepartment];
        }
        return null;
    }
}