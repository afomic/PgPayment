package afomic.com.pgpayment.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import afomic.com.pgpayment.R;
import afomic.com.pgpayment.ui.main.MainActivity;
import afomic.com.pgpayment.ui.otp.OtpActivity;

public class PaymentSuccessfulActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_successful);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(PaymentSuccessfulActivity.this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
}
