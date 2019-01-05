package afomic.com.pgpayment.ui.paymentOverview;

import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.helper.FacultyHelper;
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
        int amount = FacultyHelper.getFacultySchoolFees(payment.getFaculty());
        if (!payment.getType().equals("Harmattan and rain Semester")) {
            amount = amount / 2;
        }
        payment.setAmount(amount);
        payment.setFaculty(user.getFaculty());
        mOverviewView.showPaymentDetails(payment);
    }
}
