package afomic.com.pgpayment.data;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import afomic.com.pgpayment.model.PaymentHistory;

@Database(entities = {PaymentHistory.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract PaymentHistoryDao getPaymentHistoryDao();
}
