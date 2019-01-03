package afomic.com.pgpayment.ui.main;

import afomic.com.pgpayment.ui.base.BaseView;

public interface MainView extends BaseView {
    void showPaymentOverview();
    void showProfile();
    void showPaymentHistory();
    void showSettings();
}
