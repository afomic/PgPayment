package afomic.com.pgpayment.ui.paymentOverview;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;
import android.widget.Toast;

import afomic.com.pgpayment.Constants;
import afomic.com.pgpayment.R;
import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.model.Payment;
import afomic.com.pgpayment.ui.PaymentDetail.PaymentDetailActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class PaymentOverviewFragment extends Fragment implements PaymentOverviewView {
    @BindView(R.id.spn_payment_type)
    Spinner paymentTypeSpinner;

    @BindView(R.id.spn_section)
    Spinner sectionSpinner;

    public static String TAG = "PaymentOverviewFragment";

    private PaymentOverviewPresenter mPaymentOverviewPresenter;
    private String selectedPaymentType;
    private String selectedSection;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_payment_overview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mPaymentOverviewPresenter = new PaymentOverviewPresenter(this, AuthManger.getInstance());
    }


    @Override
    public void initView() {

    }

    @Override
    public void initListeners() {
        sectionSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] availableSections = getResources().getStringArray(R.array.sections);
                selectedSection = availableSections[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        paymentTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String[] availablePaymentType = getResources().getStringArray(R.array.payment_types);
                selectedPaymentType = availablePaymentType[i];
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void notifyError(String reason) {
        Toast.makeText(getContext(), reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showPaymentDetails(Payment payment) {
        Intent intent = new Intent(getContext(), PaymentDetailActivity.class);
        intent.putExtra(Constants.EXTRA_PAYMENT, payment);
        startActivity(intent);
    }

    @OnClick(R.id.btn_make_payment)
    public void makePayment() {
        Payment schoolFeesPayment = new Payment();
        schoolFeesPayment.setSection(selectedSection);
        schoolFeesPayment.setType(selectedPaymentType);
        mPaymentOverviewPresenter.makePayment(schoolFeesPayment);
    }

}
