package com.example.linux.muscleapp.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.ui.login.contract.LoginContract;
import com.example.linux.muscleapp.ui.login.fragment.SignInFragment;
import com.example.linux.muscleapp.ui.login.fragment.SignUpFragment;
import com.example.linux.muscleapp.ui.login.presenter.LoginPresenter;
import com.example.linux.muscleapp.ui.session.MainActivity;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is a login with different options
 */

public class LogInActivity extends AppCompatActivity implements SignInFragment.LoginListener,SignUpFragment.SignupListener  {
    private SignInFragment signInFragmentFragment;
    private SignUpFragment signUpFragment;
    private LoginContract.LoginPresenter signinPresenter;
    private LoginContract.SignupPresenter signupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        signInFragmentFragment = (SignInFragment) getSupportFragmentManager().findFragmentByTag(SignInFragment.TAG);

        if(signInFragmentFragment == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            signInFragmentFragment = SignInFragment.newInstance(null);
            transaction.add(android.R.id.content, signInFragmentFragment, SignInFragment.TAG).commit();
        }
        signinPresenter = new LoginPresenter(signInFragmentFragment);
        signInFragmentFragment.setPresenter(signinPresenter);

    }


    @Override
    public void goSignUp() {
        signUpFragment = (SignUpFragment) getSupportFragmentManager().findFragmentByTag(SignUpFragment.TAG);
        if(signUpFragment == null){
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            signUpFragment = SignUpFragment.newInstance(null);
            transaction.addToBackStack(null);
            transaction.replace(android.R.id.content,signUpFragment,SignUpFragment.TAG).commit();
        }
        signupPresenter = new SignupPresenter(signUpFragment);
        signUpFragment.setPresenter(signupPresenter);

    }

    @Override
    public void goMain() {
        startActivity(new Intent(LogInActivity.this, MainActivity.class));
    }

    @Override
    public void goLogin() {
        getSupportFragmentManager().popBackStack();
    }
}
