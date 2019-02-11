package afomic.com.pgpayment.ui.PaymentDetail;

import afomic.com.pgpayment.model.Payment;
import afomic.com.pgpayment.ui.base.BaseView;

public interface PaymentDetailView extends BaseView {
    void showPaymentDetail(Payment payment);

    void showPaystackView(Payment payment);
}
