package afomic.com.pgpayment.ui.profile;

import afomic.com.pgpayment.helper.AuthManger;

public class ProfilePresenter {
    private ProfileView view;
    private AuthManger mAuthManger;

    public ProfilePresenter(ProfileView profileView, AuthManger authManger) {
        view = profileView;
        mAuthManger = authManger;
    }
    public void loadPage(){
        view.showUser(mAuthManger.getCurrentUser());
    }
}
