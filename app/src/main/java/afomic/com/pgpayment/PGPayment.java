package afomic.com.pgpayment;

import android.app.Application;
import android.content.Context;

public class PGPayment extends Application {
    private static Application sApplication;

    @Override
    public void onCreate() {
        super.onCreate();
        sApplication = this;
    }

    public static Context getContext() {
        return sApplication.getApplicationContext();

    }
}
