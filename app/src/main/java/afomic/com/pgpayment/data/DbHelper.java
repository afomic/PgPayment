package afomic.com.pgpayment.data;

import android.arch.persistence.room.Room;

import afomic.com.pgpayment.PGPayment;

public class DbHelper {
    public static AppDatabase db;

    static {
        db = Room.databaseBuilder(PGPayment.getContext(),
                AppDatabase.class, "pgpayment.db")
                .allowMainThreadQueries()
                .build();
    }
}
