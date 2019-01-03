package afomic.com.pgpayment.ui.signUp;

import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.helper.FacultyHelper;
import afomic.com.pgpayment.model.User;

public class SignUpPresenter {
    private AuthManger mAuthManger;
    private SignUpView mSignUpView;
    private String[] departmentList = {};

    public SignUpPresenter(SignUpView signUpView, AuthManger authManger) {
        mSignUpView = signUpView;
        mAuthManger = authManger;
        signUpView.intView();
        signUpView.initListeners();
    }

    public void signUpUser(User user) {

    }

    public void populateDepartment(String selectedFaculty) {
        departmentList=FacultyHelper.getDepartments(selectedFaculty);
        mSignUpView.showFacultyDepartments(departmentList);
    }

    public String getDepartmentName(int selectedDepartment) {
        return null;
    }
}
