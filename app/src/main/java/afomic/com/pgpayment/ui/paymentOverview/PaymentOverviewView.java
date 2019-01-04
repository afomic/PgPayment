package afomic.com.pgpayment.ui.paymentOverview;

import afomic.com.pgpayment.model.Payment;
import afomic.com.pgpayment.ui.base.BaseView;

interface PaymentOverviewView extends BaseView {
    void notifyError(String reason);

    void showPaymentDetails(Payment payment);
}
