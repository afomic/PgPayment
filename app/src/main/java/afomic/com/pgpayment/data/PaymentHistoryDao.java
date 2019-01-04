package afomic.com.pgpayment.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Query;

import java.util.List;

import afomic.com.pgpayment.model.PaymentHistory;

public interface PaymentHistoryDao extends BaseDao<PaymentHistory> {
    @Query("SELECT * FROM paymenthistory")
    LiveData<List<PaymentHistory>> getPaymentHistory();
}
