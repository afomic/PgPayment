package afomic.com.pgpayment.ui.signUp;

import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.helper.FacultyHelper;
import afomic.com.pgpayment.helper.StringUtils;
import afomic.com.pgpayment.model.User;

public class SignUpPresenter {
    private AuthManger mAuthManger;
    private SignUpView mSignUpView;
    private String[] departmentList = {};

    public SignUpPresenter(SignUpView signUpView, AuthManger authManger) {
        mSignUpView = signUpView;
        mAuthManger = authManger;
        signUpView.initView();
        signUpView.initListeners();
    }

    public void signUpUser(User user) {
        if (!StringUtils.isValidMatricNumber(user.getMatricNumber())) {
            mSignUpView.notifySignUpFail("Please enter valid matric number");
            return;
        }
        if (StringUtils.isEmpty(user.getEmail())) {
            mSignUpView.notifySignUpFail("Please enter your email");
            return;
        }
        if (StringUtils.isEmpty(user.getFaculty())) {
            mSignUpView.notifySignUpFail("Please select a faculty");
            return;
        }
        if (StringUtils.isEmpty(user.getDepartment())) {
            mSignUpView.notifySignUpFail("Please select a deparment");
            return;
        }
        if (StringUtils.isEmpty(user.getFirstName())) {
            mSignUpView.notifySignUpFail("Please provide a first name");
            return;
        }
        if (StringUtils.isEmpty(user.getLastName())) {
            mSignUpView.notifySignUpFail("Please provide a last name");
            return;
        }
        signUp(user);
    }

    private void signUp(User user) {
        mSignUpView.showProgress();
        mAuthManger.signUp(user, new AuthManger.AuthManagerCallback() {
            @Override
            public void onSuccess(User user) {
                mSignUpView.hideProgress();
                mSignUpView.showMainView();

            }

            @Override
            public void onFailure(String reason) {
                mSignUpView.hideProgress();
                mSignUpView.notifySignUpFail(reason);
            }
        });
    }

    public void populateDepartment(String selectedFaculty) {
        departmentList = FacultyHelper.getDepartments(selectedFaculty);
        mSignUpView.showFacultyDepartments(departmentList);
    }

    public String getDepartmentName(int selectedDepartment) {
        if (departmentList.length > 0) {
            return departmentList[selectedDepartment];
        }
        return null;
    }
}