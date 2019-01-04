package afomic.com.pgpayment.ui.PaymentDetail;

import android.os.Bundle;

import afomic.com.pgpayment.Constants;
import afomic.com.pgpayment.model.Payment;

public class PaymentDetailPresenter {
    private PaymentDetailView mPaymentDetailView;
    private Payment currentPayment;

    public PaymentDetailPresenter(PaymentDetailView view) {
        mPaymentDetailView = view;
        view.initView();
        view.initListeners();
    }

    public void loadData(Bundle bundle) {
        currentPayment = bundle.getParcelable(Constants.EXTRA_PAYMENT);
        if (currentPayment != null) {
            mPaymentDetailView.showPaymentDetail(currentPayment);
        }
    }

    public void handleMakePayment() {

    }
}
