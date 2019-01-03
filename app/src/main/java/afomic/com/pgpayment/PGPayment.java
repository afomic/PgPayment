package afomic.com.pgpayment;

import android.app.Application;
import android.content.Context;

public class PGPayment extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    public Context getContext() {
        return getApplicationContext();
    }
}
