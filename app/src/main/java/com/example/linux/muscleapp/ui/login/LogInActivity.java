package com.example.linux.muscleapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.linux.muscleapp.R;
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

public class LogInActivity extends AppCompatActivity {
    @BindView(R.id.btnLoginSignIn) Button btnSignin;
    @BindView(R.id.txvLoginSignUp) TextView txvSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        ButterKnife.bind(this);

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
                Intent intent = new Intent(LogInActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }
}
