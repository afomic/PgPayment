package afomic.com.pgpayment.ui.paystack;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.text.NumberFormat;

import afomic.com.pgpayment.Constants;
import afomic.com.pgpayment.R;
import afomic.com.pgpayment.model.Payment;
import app.ephod.pentecost.library.paystack.PaymentView;

public class WebPaymentActivity extends AppCompatActivity {
    PaymentView paymentView;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_payment);
        paymentView = findViewById(R.id.paymentView);
        button = paymentView.getPayButton();
        Payment payment = getIntent().getParcelableExtra(Constants.EXTRA_PAYMENT);
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
            }
        });

        paymentView.setBanksSpinner(arraySpinner);
        paymentView.setPentecostBackgroundColor(getResources().getColor(R.color.colorPrimary));

        paymentView.getHeaderContentView().setTextColor(getResources().getColor(R.color.cardview_dark_background));
        String amount = "₦" + NumberFormat.getNumberInstance().format(payment.getAmount());
        paymentView.setBillContent(amount);
        paymentView.setChargeListener(new PaymentView.ChargeListener() {
            @Override
            public void onChargeCard() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(WebPaymentActivity.this,"SuccessFull",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, 3000);
            }

            @Override
            public void onChargeBank() {

            }

            @Override
            public void onSuccess() {

            }
        });

    }
}
