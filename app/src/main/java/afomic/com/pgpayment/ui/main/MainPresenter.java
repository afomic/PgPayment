package afomic.com.pgpayment.ui.main;

import afomic.com.pgpayment.R;

public class MainPresenter {
    private MainView mMainView;

    public MainPresenter(MainView view) {
        mMainView = view;
        view.initView();
        view.initListeners();
    }


}
