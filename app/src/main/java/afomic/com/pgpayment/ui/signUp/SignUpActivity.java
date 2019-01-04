package afomic.com.pgpayment.ui.signUp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import afomic.com.pgpayment.PGPayment;
import afomic.com.pgpayment.R;
import afomic.com.pgpayment.helper.AuthManger;
import afomic.com.pgpayment.model.User;
import afomic.com.pgpayment.ui.main.MainActivity;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends AppCompatActivity implements SignUpView {
    @BindView(R.id.edt_email)
    EditText emailEditText;
    @BindView(R.id.edt_matric_number)
    EditText matricEditText;
    @BindView(R.id.edt_first_name)
    EditText firstNameEditText;
    @BindView(R.id.edt_last_name)
    EditText lastNameEditText;
    @BindView(R.id.edt_password)
    EditText passwordEditText;
    @BindView(R.id.edt_confirm)
    EditText confrimPaswordEditText;
    @BindView(R.id.spn_department)
    Spinner deparmentSpinner;
    @BindView(R.id.spn_faculty)
    Spinner facutytSpinner;

    private SignUpPresenter mSignUpPresenter;
    private String selectedFaculty, selectedDepartment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        mSignUpPresenter = new SignUpPresenter(this, AuthManger.getInstance(PGPayment.getContext()));

    }

    @Override
    public void initView() {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void showFacultyDepartments(String[] departments) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(SignUpActivity.this, android.R.layout.simple_list_item_1, departments);
        deparmentSpinner.setAdapter(adapter);
    }

    @Override
    public void enableDepartmentSpinner() {
        deparmentSpinner.setEnabled(true);
    }


    @Override
    public void initListeners() {
        facutytSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (i != 0) {
                    String[] faculties = getResources().getStringArray(R.array.faculty);
                    selectedFaculty = faculties[i];
                    mSignUpPresenter.populateDepartment(selectedFaculty);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        deparmentSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedDepartment = mSignUpPresenter.getDepartmentName(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @OnClick(R.id.btn_sign_up)
    public void signUpButtonClick() {
        String email = emailEditText.getText().toString();
        String firstName = firstNameEditText.getText().toString();
        String lastname = lastNameEditText.getText().toString();
        String phoneNumber = emailEditText.getText().toString();
        String matricNumber = matricEditText.getText().toString();
        String password = passwordEditText.getText().toString();
        String checkPassword = confrimPaswordEditText.getText().toString();
        if (password.equals(checkPassword)) {
            User user = new User();
            user.setEmail(email);
            user.setFirstName(firstName);
            user.setLastName(lastname);
            user.setMatricNumber(matricNumber);
            user.setMobileNumber(phoneNumber);
            user.setPassword(password);
            user.setDepartment(selectedDepartment);
            user.setFaculty(selectedFaculty);
            mSignUpPresenter.signUpUser(user);
        } else {
            notifySignUpFail("Password does not match");
        }
    }

    @Override
    public void notifySignUpFail(String reason) {
        Toast.makeText(SignUpActivity.this, reason, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showMainView() {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
