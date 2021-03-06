package com.example.linux.muscleapp.ui.login;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.User;
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
        this.interactor = new SignupInteractorImp(this);
    }
    @Override
    public void add(String email, String pass, String name, String residence, String date) {
        interactor.add(email,pass,name,residence,date);

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

    @Override
    public void onErrorEmail() {
        view.setErrorEmail();
    }

    @Override
    public void onErrorPass() {
        view.setErrorPass();
    }

    @Override
    public void openDialog() {
        view.openDialog();
    }

    @Override
    public void closeDialog(boolean res) {
        view.closeDialog(res);
    }


}

