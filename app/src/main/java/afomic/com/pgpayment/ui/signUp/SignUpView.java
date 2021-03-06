package afomic.com.pgpayment.ui.signUp;

import afomic.com.pgpayment.ui.base.BaseView;

public interface SignUpView extends BaseView {
    void showFacultyDepartments(String[] departments);

    void enableDepartmentSpinner();

    void notifySignUpFail(String reason);

    void showOtpVerification(String otp);
}
