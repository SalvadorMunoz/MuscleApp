package com.example.linux.muscleapp.ui.signup;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.ui.base.BaseActivity;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 2.0
 *
 * This class is a sign up window with user's information
 */

public class SignUpActivity extends BaseActivity implements SignUpView{
    @BindView(R.id.imgSignUpDate) ImageView imgDate;
    @BindView(R.id.txvRes) TextView res;
    @BindView(R.id.btnSignUpCreateUser) Button btnCreate;
    @BindView(R.id.toolbar)Toolbar toolbar;
    @BindView(R.id.edtSingupEmail) EditText edtEmail;
    @BindView(R.id.edtSingupPass) EditText edtPass;
    @BindView(R.id.edtSingupName) EditText edtName;
    @BindView(R.id.edtSingupResidence) EditText edtResidence;


    private SignupPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        ButterKnife.bind(this);
        toolbar.setTitle(R.string.sign_up);

        presenter = new SignupPresenterImp(this);

        //Listener for DaterPickerDialog
        final DatePickerDialog.OnDateSetListener dateSetListener= new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                //Show date selected
                res.setText(String.format("%04d-%02d-%02d",i,i1,i2));
            }
        };

        //Open DatePickerDialog when you click on the calendar image
        imgDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //create datepicker (context activity, style, listener,year, month,day)
                DatePickerDialog dpdDate = new DatePickerDialog(SignUpActivity.this,
                        R.style.DatePicker,dateSetListener,
                        Calendar.getInstance().get(Calendar.YEAR),
                        Calendar.getInstance().get(Calendar.MONTH),
                        Calendar.getInstance().get(Calendar.DAY_OF_MONTH));                //Set max limit on current date
                dpdDate.getDatePicker().setMaxDate(Calendar.getInstance().getTimeInMillis());

                dpdDate.show();
            }
        });
        btnCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.add(edtEmail.getText().toString(),edtPass.getText().toString(),edtName.getText().toString(),edtResidence.getText().toString(),res.getText().toString());
            }
        });
    }


    @Override
    public void setEmptyEmail() {
        showSnackBar(R.string.err_emptyemail);
    }

    @Override
    public void setEmailExists() {
       showSnackBar(R.string.err_emailexists);
    }

    @Override
    public void setEmptyPass() {
        showSnackBar(R.string.err_emptypass);
    }

    @Override
    public void setEmptyResidence() {
        showSnackBar(R.string.err_emptyresidence);
    }

    @Override
    public void setEmptyBornDate() {
        showSnackBar(R.string.err_emptyborndate);
    }

    @Override
    public void setEmptyName() {
        showSnackBar(R.string.err_emptyname);
    }

    @Override
    public void goLogin() {
        finish();
    }
}
