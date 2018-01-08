package com.example.linux.muscleapp.ui.login.interactor;

/**
 * Created by linux on 15/11/17.
 */

public interface SignupInteractor {
    void add(String email, String pass, String name,String residence,String bornDate,OnSignupFinish onSignupFinish);
    interface OnSignupFinish{
        void onSuccess();
        void onEmptyEmail();
        void onEmptyPass();
        void onEmptyName();
        void onEmptyResidence();
        void onEmptyBornDate();
        void onEmailExists();
        void onErrorEmail();
        void onErrorPass();
    }
}
