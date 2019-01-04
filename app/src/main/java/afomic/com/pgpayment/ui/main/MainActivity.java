package afomic.com.pgpayment.ui.main;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;

import afomic.com.pgpayment.R;
import afomic.com.pgpayment.ui.paymentHistory.PaymentHistoryFragment;
import afomic.com.pgpayment.ui.paymentOverview.PaymentOverviewFragment;

public class MainActivity extends AppCompatActivity implements MainView {
    private DrawerLayout mDrawerLayout;
    private NavigationView mNavigationView;
    private FragmentManager mFragmentManager;

    private MainPresenter mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mFragmentManager = getSupportFragmentManager();
        mMainPresenter = new MainPresenter(this);
    }


    private void loadPaymentOverview() {
        PaymentOverviewFragment frag = new PaymentOverviewFragment();
        mFragmentManager.beginTransaction().add(R.id.main_container, frag)
                .addToBackStack(PaymentOverviewFragment.TAG).commit();
    }

    private void showFragment(Fragment fragment, String tag) {
        mFragmentManager.beginTransaction().replace(R.id.main_container, fragment)
                .addToBackStack(tag).commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            mDrawerLayout.openDrawer(Gravity.START);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showPaymentOverview() {
        PaymentOverviewFragment frag = new PaymentOverviewFragment();
        showFragment(frag, PaymentOverviewFragment.TAG);
    }

    @Override
    public void showProfile() {

    }

    @Override
    public void initView() {
        mDrawerLayout = findViewById(R.id.drawer_view);
        mNavigationView = findViewById(R.id.navigation_container);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_dehaze);
        loadPaymentOverview();
    }

    @Override
    public void initListeners() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                mDrawerLayout.closeDrawers();
                menuItem.setChecked(true);
                switch (menuItem.getItemId()) {
                    case R.id.menu_payment_history:
                        showPaymentHistory();
                        break;
                    case R.id.menu_home:
                        showPaymentOverview();
                        break;
                }
                return false;
            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showPaymentHistory() {
        PaymentHistoryFragment fragment = new PaymentHistoryFragment();
        showFragment(fragment, PaymentHistoryFragment.TAG);
    }
}
