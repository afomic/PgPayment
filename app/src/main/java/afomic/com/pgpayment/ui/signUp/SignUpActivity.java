package afomic.com.pgpayment.ui.signUp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Spinner;

import afomic.com.pgpayment.R;
import butterknife.BindView;

public class SignUpActivity extends AppCompatActivity {
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
    }
}
