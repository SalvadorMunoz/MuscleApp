package com.example.linux.muscleapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.ui.base.BaseActivity;
import com.example.linux.muscleapp.ui.session.MainActivity;
import com.example.linux.muscleapp.ui.signup.SignUpActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is a login with different options
 */

public class LogInActivity extends BaseActivity implements LoginView {
    @BindView(R.id.edtLoginEmail) EditText edtEmail;
    @BindView(R.id.edtLoginPass) EditText edtPass;

    @BindView(R.id.btnLoginSignIn) Button btnSignin;
    @BindView(R.id.txvLoginSignUp) TextView txvSignUp;
    LoginPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

        presenter = new LoginPresenterImp(this);

        txvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LogInActivity.this,SignUpActivity.class);
                startActivity(intent);
            }
        });
        btnSignin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.validateCredentials(edtEmail.getText().toString(),edtPass.getText().toString());
            }
        });
    }

    @Override
    public void goMainActivity() {
        Intent intent = new Intent(LogInActivity.this,MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void setEmptyEmail() {
        showSnackBar(R.string.err_emptyemail);
    }

    @Override
    public void setEmptyPass() {
        showSnackBar(R.string.err_emptypass);
    }

    @Override
    public void setSigninError() {
        showSnackBar(R.string.err_signin);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        presenter = null;
    }
}
