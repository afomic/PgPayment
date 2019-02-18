package afomic.com.pgpayment.ui.paystack;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.gson.reflect.TypeToken;

import java.text.NumberFormat;

import afomic.com.pgpayment.Constants;
import afomic.com.pgpayment.R;
import afomic.com.pgpayment.helper.Common;
import afomic.com.pgpayment.helper.SharedPreferenceManager;
import afomic.com.pgpayment.helper.StringUtils;
import afomic.com.pgpayment.model.Payment;
import afomic.com.pgpayment.model.PaymentHistory;
import afomic.com.pgpayment.model.User;
import afomic.com.pgpayment.network.ApiService;
import afomic.com.pgpayment.network.param.SendSmsRequest;
import afomic.com.pgpayment.network.param.SendSmsResponse;
import afomic.com.pgpayment.ui.otp.OtpActivity;
import app.ephod.pentecost.library.paystack.PaymentView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WebPaymentActivity extends AppCompatActivity {
    PaymentView paymentView;
    Button button;
    Payment mPayment;
    RelativeLayout progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_payment);
        paymentView = findViewById(R.id.paymentView);
        button = paymentView.getPayButton();
        progress = findViewById(R.id.progress_layout);
        setTitle(" Web Payment");
        mPayment = getIntent().getParcelableExtra(Constants.EXTRA_PAYMENT);
        String[] arraySpinner = new String[]{
                "Access Bank", "Citibank", "Diamond Bank", "Dynamic Standard Bank", "Ecobank Nigeria", "Fidelity Bank Nigeria",
                "First Bank of Nigeria", "First City Monument Bank", "Guaranty Trust Bank", "Heritage Bank Plc", "Jaiz Bank",
                "Keystone Bank Limited", "Providus Bank Plc", "Skye Bank", "Stanbic IBTC Bank Nigeria Limited", "Standard Chartered Bank",
                "Sterling Bank", "Suntrust Bank Nigeria Limited", "Union Bank of Nigeria", "United Bank for Africa", "Unity Bank Plc",
                "Wema Bank", "Zenith Bank"
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                paymentView.showLoader();
                String cvv = paymentView.getCardCCV();
                String cardNumber = paymentView.getCardNumber();
                String expiryDate = paymentView.getCardExpDate();
                if (cvv.length() != 3) {
                    Toast.makeText(WebPaymentActivity.this, "CVV must be 3 Digit", Toast.LENGTH_SHORT).show();
                    paymentView.hideLoader();
                    return;
                }
                if (cardNumber.length() != 16) {
                    Toast.makeText(WebPaymentActivity.this, "Card number incorrect", Toast.LENGTH_SHORT).show();
                    paymentView.hideLoader();
                    return;
                }
                if (expiryDate.length() != 5) {
                    Toast.makeText(WebPaymentActivity.this, "Invalid expiry date", Toast.LENGTH_SHORT).show();
                    paymentView.hideLoader();
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                SharedPreferenceManager preferenceManager = new SharedPreferenceManager(WebPaymentActivity.this);
                String userString = preferenceManager.getStringPref(SharedPreferenceManager.PREF_USER);
                final User user = (User) Common.parseJSONToObject(userString, TypeToken.get(User.class));
                final String otp = StringUtils.getOTP();
                final SendSmsRequest sendSmsRequest = new SendSmsRequest();
                sendSmsRequest.sender = "PGPayment";
                sendSmsRequest.recipient = user.getMobileNumber();
                sendSmsRequest.message = "Kindly verify payment \n Otp  " + otp;
                ApiService.getInstance(WebPaymentActivity.this).mPgPaymentApi.sendSms(sendSmsRequest).enqueue(new Callback<SendSmsResponse>() {
                    @Override
                    public void onResponse(Call<SendSmsResponse> call, Response<SendSmsResponse> response) {
                        progress.setVisibility(View.GONE);
                        if (response.isSuccessful()) {
                            SendSmsResponse smsResponse = response.body();
                            if (smsResponse.code == 200) {

                                PaymentHistory paymentHistory = new PaymentHistory();
                                paymentHistory.setAmount(mPayment.getAmount());
                                paymentHistory.setSection(mPayment.getSection());
                                paymentHistory.setStatus(true);
                                paymentHistory.setTransactionId(StringUtils.getRandomString(16));
                                Intent intent = new Intent(WebPaymentActivity.this, OtpActivity.class);
                                intent.putExtra(Constants.EXTRA_TYPE, Constants.OTP_PAYMENT_VERIFICATION);
                                intent.putExtra(Constants.EXTRA_OTP, otp);
                                intent.putExtra(Constants.EXTRA_PAYMENT, paymentHistory);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }

                    @Override
                    public void onFailure(Call<SendSmsResponse> call, Throwable t) {
                        progress.setVisibility(View.VISIBLE);
                        Toast.makeText(WebPaymentActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        paymentView.setBanksSpinner(arraySpinner);
        paymentView.setPentecostBackgroundColor(getResources().getColor(R.color.colorPrimary));

        paymentView.getHeaderContentView().setTextColor(getResources().getColor(R.color.cardview_dark_background));
        String amount = "₦" + NumberFormat.getNumberInstance().format(mPayment.getAmount());
        paymentView.setBillContent(amount);


    }
}
