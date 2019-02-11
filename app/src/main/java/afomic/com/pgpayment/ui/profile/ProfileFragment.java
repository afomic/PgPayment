package afomic.com.pgpayment.ui.profile;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import afomic.com.pgpayment.R;
import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.model.User;
import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileFragment extends Fragment implements ProfileView {
    @BindView(R.id.tv_department)
    TextView departmentTextView;
    @BindView(R.id.tv_name)
    TextView nameTextView;
    @BindView(R.id.tv_email)
    TextView emailTextView;
    @BindView(R.id.tv_phone)
    TextView phoneNumberTextView;
    @BindView(R.id.tv_matric_number)
    TextView matricNumberTextView;

    private ProfilePresenter mProfilePresenter;
    public static final String TAG = "ProfileFragment";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mProfilePresenter = new ProfilePresenter(this, AuthManger.getInstance());
        mProfilePresenter.loadPage();
    }

    @Override
    public void showUser(User user) {
        phoneNumberTextView.setText(user.getMobileNumber());
        matricNumberTextView.setText(user.getMatricNumber());
        emailTextView.setText(user.getEmail());
        departmentTextView.setText(user.getDepartment());
        String name = user.getFirstName() + " " + user.getLastName();
        nameTextView.setText(name);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListeners() {

    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
