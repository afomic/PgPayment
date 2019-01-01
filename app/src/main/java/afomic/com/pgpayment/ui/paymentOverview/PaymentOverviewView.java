package afomic.com.pgpayment.ui.paymentOverview;

import afomic.com.pgpayment.ui.base.BaseView;

interface PaymentOverviewView extends BaseView {
    void showPaymentView();

    void notifyError(String reason);
}
