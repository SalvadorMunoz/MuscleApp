package com.example.linux.muscleapp.ui.login.interactor;

/**
 * Created by linux on 14/11/17.
 */

public interface LoginInteractor {
    void validateCredentials(String email, String pass, OnLoginFinished loginFinished);
    interface  OnLoginFinished{
        void onEmptyEmail();
        void onSigninError();
        void onEmptyPass();
        void onSucces(String email);
    }
}
