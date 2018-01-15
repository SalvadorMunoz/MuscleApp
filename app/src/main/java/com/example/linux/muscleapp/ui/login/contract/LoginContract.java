package com.example.linux.muscleapp.ui.login.contract;

/**
 * Created by linux on 27/11/17.
 */

public interface LoginContract {
    interface LoginPresenter {
        void validateCredentials(String email, String pass);
        void onDestroy();
    }
    interface SignupPresenter {
        void add(String email,String pass,String name, String residence,String date);
        void onDestroy();
    }

     interface LoginView {
        void setPresenter(LoginContract.LoginPresenter  presenter);
        void goMainActivity();
        void setEmptyEmail();
        void setEmptyPass();
        void setSigninError();
    }

    interface SignUpView {
        void setPresenter(LoginContract.SignupPresenter  presenter);
        void setEmptyEmail();
        void setEmailExists();
        void setEmptyPass();
        void setEmptyResidence();
        void setEmptyBornDate();
        void setEmptyName();
        void setErrorEmail();
        void setErrorPass();
        void goLogin();
    }



}