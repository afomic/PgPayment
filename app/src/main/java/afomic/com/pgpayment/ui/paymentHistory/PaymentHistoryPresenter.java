package afomic.com.pgpayment.ui.paymentHistory;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;

import java.util.List;

import afomic.com.pgpayment.data.DbHelper;
import afomic.com.pgpayment.model.PaymentHistory;

public class PaymentHistoryPresenter {
    private PaymentHistoryView mPaymentHistoryView;

    public PaymentHistoryPresenter(PaymentHistoryView view) {
        this.mPaymentHistoryView = view;
        view.initView();
        view.initListeners();
    }

    public void loadData(Fragment fragment) {
        mPaymentHistoryView.showProgress();
        DbHelper.db.getPaymentHistoryDao().getPaymentHistory().observe(fragment, new Observer<List<PaymentHistory>>() {
            @Override
            public void onChanged(@Nullable List<PaymentHistory> paymentHistories) {
                mPaymentHistoryView.hideProgress();
                if (paymentHistories == null || paymentHistories.size() == 0) {
                    mPaymentHistoryView.showEmptyListView();
                } else {
                    mPaymentHistoryView.hideEmptyListView();
                    mPaymentHistoryView.showPaymentHistory(paymentHistories);
                }
            }
        });
    }


}
