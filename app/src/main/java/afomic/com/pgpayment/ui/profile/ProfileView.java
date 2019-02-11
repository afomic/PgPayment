package afomic.com.pgpayment.ui.profile;

import afomic.com.pgpayment.model.User;
import afomic.com.pgpayment.ui.base.BaseView;

public interface ProfileView extends BaseView {
    void showUser(User user);
}
