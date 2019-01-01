package afomic.com.pgpayment.ui.paymentOverview;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Spinner;

import afomic.com.pgpayment.R;
import butterknife.BindView;
import butterknife.OnClick;

public class PaymentOverviewFragment extends Fragment implements PaymentOverviewView {
    @BindView(R.id.spn_payment_type)
    Spinner paymentTypeSpinner;

    @BindView(R.id.spn_section)
    Spinner sectionSpinner;
    private PaymentOverviewPresenter mPaymentOverviewPresenter;

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
        intView(view);
    }

    @Override
    public void intView(View view) {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void showPaymentView() {

    }

    @Override
    public void notifyError(String reason) {

    }

    @OnClick(R.id.btn_make_payment)
    public void makePayment(){

    }
}
