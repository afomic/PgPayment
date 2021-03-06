package afomic.com.pgpayment.ui.PaymentDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import afomic.com.pgpayment.Constants;
import afomic.com.pgpayment.R;
import afomic.com.pgpayment.model.Payment;
import afomic.com.pgpayment.ui.paystack.WebPaymentActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentDetailActivity extends AppCompatActivity implements PaymentDetailView {
    @BindView(R.id.tv_amount)
    EditText amountTextView;
    @BindView(R.id.tv_department)
    TextView departmentTextView;
    @BindView(R.id.tv_faculty)
    TextView facultyTextView;
    @BindView(R.id.tv_matric_number)
    TextView matricNumberTextView;
    @BindView(R.id.tv_payment_type)
    TextView paymentTypeTextView;
    @BindView(R.id.tv_section)
    TextView sectionTextView;
    private PaymentDetailPresenter mPaymentDetailPresenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment_detail);
        ButterKnife.bind(this);
        mPaymentDetailPresenter = new PaymentDetailPresenter(this);
        mPaymentDetailPresenter.loadData(getIntent().getExtras());
    }

    @OnClick(R.id.btn_make_payment)
    public void makePaymentButtonClick() {
        mPaymentDetailPresenter.handleMakePayment(amountTextView.getText().toString());
    }

    @Override
    public void showPaymentDetail(Payment payment) {
        matricNumberTextView.setText(payment.getMatricNumber());
        sectionTextView.setText(payment.getSection());
        facultyTextView.setText(payment.getFaculty());
        String amount = String.valueOf(payment.getAmount());
        amountTextView.setText(amount);
        departmentTextView.setText(payment.getDepartment());
        paymentTypeTextView.setText(payment.getType());

    }

    @Override
    public void initView() {
        setTitle("Payment Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showPaystackView(Payment payment) {
        Intent intent = new Intent(PaymentDetailActivity.this, WebPaymentActivity.class);
        intent.putExtra(Constants.EXTRA_PAYMENT, payment);
        startActivity(intent);
    }
}
