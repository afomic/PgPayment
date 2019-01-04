package afomic.com.pgpayment.ui.paymentOverview;

import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.model.Payment;
import afomic.com.pgpayment.model.User;

public class PaymentOverviewPresenter {
    private PaymentOverviewView mOverviewView;
    private AuthManger mAuthManger;

    public PaymentOverviewPresenter(PaymentOverviewView paymentOverviewView, AuthManger authManger) {
        mOverviewView = paymentOverviewView;
        mAuthManger = authManger;
        mOverviewView.initListeners();
        mOverviewView.initView();
    }

    public void makePayment(Payment payment) {
        User user = mAuthManger.getCurrentUser();
        payment.setEmail(user.getEmail());
        payment.setDepartment(user.getDepartment());
        payment.setMatricNumber(user.getMatricNumber());
        payment.setAmount(0);
        payment.setFaculty(user.getFaculty());
        mOverviewView.showPaymentDetails(payment);
    }
}
