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
import afomic.com.pgpayment.data.DbHelper;
import afomic.com.pgpayment.helper.StringUtils;
import afomic.com.pgpayment.model.Payment;
import afomic.com.pgpayment.model.PaymentHistory;
import app.ephod.pentecost.library.paystack.PaymentView;

public class WebPaymentActivity extends AppCompatActivity {
    PaymentView paymentView;
    Button button;
    Payment mPayment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_payment);
        paymentView = findViewById(R.id.paymentView);
        button = paymentView.getPayButton();
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
                if (expiryDate.length() != 4) {
                    Toast.makeText(WebPaymentActivity.this, "Invalid expiry date", Toast.LENGTH_SHORT).show();
                    paymentView.hideLoader();
                    return;
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        PaymentHistory paymentHistory = new PaymentHistory();
                        paymentHistory.setAmount(mPayment.getAmount());
                        paymentHistory.setSection(mPayment.getSection());
                        paymentHistory.setStatus(true);
                        paymentHistory.setTransactionId(StringUtils.getRandomString(16));
                        DbHelper.db.getPaymentHistoryDao().insert(paymentHistory);
                        Toast.makeText(WebPaymentActivity.this, "SuccessFull", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }, 3000);
            }
        });

        paymentView.setBanksSpinner(arraySpinner);
        paymentView.setPentecostBackgroundColor(getResources().getColor(R.color.colorPrimary));

        paymentView.getHeaderContentView().setTextColor(getResources().getColor(R.color.cardview_dark_background));
        String amount = "₦" + NumberFormat.getNumberInstance().format(mPayment.getAmount());
        paymentView.setBillContent(amount);


    }
}
