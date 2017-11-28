package com.example.linux.muscleapp.ui.login;

import com.example.linux.muscleapp.ui.login.contract.LoginContract;
import com.example.linux.muscleapp.ui.login.interactor.SignupInteractor;
import com.example.linux.muscleapp.ui.login.interactor.SignupInteractorImp;

/**
 * Created by linux on 28/11/17.
 */

class SignupPresenter implements LoginContract.SignupPresenter, SignupInteractor.OnSignupFinish {
    LoginContract.SignUpView view;
    SignupInteractorImp interactor;
    public SignupPresenter(LoginContract.SignUpView view){
        this.view = view;
        this.interactor = new SignupInteractorImp();
    }
    @Override
    public void add(String email, String pass, String name, String residence, String date) {
        interactor.add(email,pass,name,residence,date,this);
    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onSuccess() {
        this.view.goLogin();
    }

    @Override
    public void onEmptyEmail() {
        this.view.setEmptyEmail();
    }

    @Override
    public void onEmptyPass() {
        this.view.setEmptyPass();
    }

    @Override
    public void onEmptyName() {
        this.view.setEmptyName();
    }

    @Override
    public void onEmptyResidence() {
        this.view.setEmptyResidence();
    }

    @Override
    public void onEmptyBornDate() {
        this.view.setEmptyBornDate();
    }

    @Override
    public void onEmailExists() {
        this.view.setEmailExists();
    }
}

