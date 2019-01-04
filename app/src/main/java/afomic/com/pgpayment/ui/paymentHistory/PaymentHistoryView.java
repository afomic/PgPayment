package afomic.com.pgpayment.ui.paymentHistory;

import java.util.List;

import afomic.com.pgpayment.model.PaymentHistory;
import afomic.com.pgpayment.ui.base.BaseView;

public interface PaymentHistoryView extends BaseView {
    void showPaymentHistory(List<PaymentHistory> paymentHistories);

    void showEmptyListView();

    void hideEmptyListView();
}
