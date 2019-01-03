package afomic.com.pgpayment.ui.paymentOverview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import afomic.com.pgpayment.R;
import afomic.com.pgpayment.model.Payment;
import butterknife.BindView;
import butterknife.OnClick;

public class PaymentOverviewFragment extends Fragment implements PaymentOverviewView {
    @BindView(R.id.spn_payment_type)
    Spinner paymentTypeSpinner;

    @BindView(R.id.spn_section)
    Spinner sectionSpinner;

    private PaymentOverviewPresenter mPaymentOverviewPresenter;
    private int selectedPaymentType;
    private String selectedSection;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPaymentOverviewPresenter = new PaymentOverviewPresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void intView() {

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
                selectedPaymentType = i;
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
    public void showPaymentView() {

    }

    @Override
    public void notifyError(String reason) {

    }

    @OnClick(R.id.btn_make_payment)
    public void makePayment() {
        Payment schoolFeesPayment=new Payment();
        schoolFeesPayment.setSection(selectedSection);
        mPaymentOverviewPresenter.makePayment(schoolFeesPayment);
    }
}
