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

import afomic.com.pgpayment.Constants;
import afomic.com.pgpayment.R;
import afomic.com.pgpayment.data.DbHelper;
import afomic.com.pgpayment.helper.SharedPreferenceManager;
import afomic.com.pgpayment.model.PaymentHistory;
import afomic.com.pgpayment.ui.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

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

        InputMethodManager imm = (InputMethodManager)   getSystemService(Context.INPUT_METHOD_SERVICE);
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
        if (type == Constants.OTP_USER_VERIFICATION) {
            SharedPreferenceManager sharedPreferenceManager = new SharedPreferenceManager(OtpActivity.this);
            sharedPreferenceManager.saveBooleanPref(SharedPreferenceManager.PREF_USER_VERIFIED, true);
            Intent intent = new Intent(OtpActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            DbHelper.db.getPaymentHistoryDao().insert(paymentHistory);
            Toast.makeText(OtpActivity.this, "SuccessFull", Toast.LENGTH_SHORT).show();
            finish();
        }
    }
}
