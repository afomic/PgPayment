package afomic.com.pgpayment.ui.otp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.alimuzaffar.lib.pin.PinEntryEditText;
import com.google.gson.reflect.TypeToken;

import afomic.com.pgpayment.Constants;
import afomic.com.pgpayment.PGPayment;
import afomic.com.pgpayment.R;
import afomic.com.pgpayment.data.DbHelper;
import afomic.com.pgpayment.helper.Common;
import afomic.com.pgpayment.helper.SharedPreferenceManager;
import afomic.com.pgpayment.model.PaymentHistory;
import afomic.com.pgpayment.model.User;
import afomic.com.pgpayment.network.ApiService;
import afomic.com.pgpayment.network.param.SendSmsRequest;
import afomic.com.pgpayment.network.param.SendSmsResponse;
import afomic.com.pgpayment.ui.PaymentSuccessfulActivity;
import afomic.com.pgpayment.ui.login.LoginActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OtpActivity extends AppCompatActivity {
    @BindView(R.id.pin_layout)
    PinEntryEditText mPinEntryEditText;
    @BindView(R.id.btn_verify)
    Button verifyButton;
    @BindView(R.id.progress_layout)
    RelativeLayout progress;
    int type;
    PaymentHistory paymentHistory;
    String otp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_otp);
        ButterKnife.bind(this);

        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);

        otp = getIntent().getStringExtra(Constants.EXTRA_OTP);
        type = getIntent().getIntExtra(Constants.EXTRA_TYPE, 0);
        paymentHistory = getIntent().getParcelableExtra(Constants.EXTRA_PAYMENT);
        if (type == Constants.OTP_USER_VERIFICATION) {
            verifyButton.setText(R.string.verify_number);
        } else {
            verifyButton.setText(R.string.verify_payment);
        }


    }

    @OnClick(R.id.btn_verify)
    public void verifyOtp() {
        progress.setVisibility(View.VISIBLE);
        String enteredOtp = mPinEntryEditText.getText().toString();
        if (enteredOtp.equals(otp)) {
            if (type == Constants.OTP_USER_VERIFICATION) {
                SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(OtpActivity.this);
                sharedPreferenceManager.saveBooleanPref(SharedPreferenceManager.PREF_USER_VERIFIED, true);
                Intent intent = new Intent(OtpActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            } else {
                DbHelper.db.getPaymentHistoryDao().insert(paymentHistory);
                Toast.makeText(OtpActivity.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(OtpActivity.this, PaymentSuccessfulActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                finish();
            }
        } else {
            progress.setVisibility(View.GONE);
            Toast.makeText(OtpActivity.this, "Invalid Otp", Toast.LENGTH_SHORT).show();
        }

    }

    @OnClick(R.id.btn_resend)
    public void resendOtp() {
        progress.setVisibility(View.VISIBLE);
        final SendSmsRequest sendSmsRequest = new SendSmsRequest();
        sendSmsRequest.sender = "PGPayment";
        SharedPreferenceManager sharedPreferenceManager= new SharedPreferenceManager(OtpActivity.this);
        String userString = sharedPreferenceManager.getStringPref(SharedPreferenceManager.PREF_USER);
        final User user = (User) Common.parseJSONToObject(userString, TypeToken.get(User.class));
        sendSmsRequest.recipient = "+" + user.getMobileNumber();
        sendSmsRequest.message = "Kindly verified your number \n Otp  " + otp;
        ApiService.getInstance(OtpActivity.this).mPgPaymentApi.sendSms(sendSmsRequest).enqueue(new Callback<SendSmsResponse>() {
            @Override
            public void onResponse(Call<SendSmsResponse> call, Response<SendSmsResponse> response) {
                progress.setVisibility(View.GONE);
                if (response.isSuccessful()) {
                    SendSmsResponse smsResponse = response.body();
                    if (smsResponse.code == 200) {
                        Toast.makeText(OtpActivity.this,"Sent",Toast.LENGTH_SHORT).show();

                    } else {
                        Toast.makeText(OtpActivity.this,"Failed to send OTP",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(Call<SendSmsResponse> call, Throwable t) {
                Toast.makeText(OtpActivity.this,"Failed to send OTP",Toast.LENGTH_SHORT).show();
                progress.setVisibility(View.GONE);
            }
        });
    }
}
