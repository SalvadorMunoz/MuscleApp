package com.example.linux.muscleapp.ui.login.presenter;


import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.login.contract.LoginContract;
import com.example.linux.muscleapp.ui.login.interactor.LoginInteractor;
import com.example.linux.muscleapp.ui.login.interactor.LoginInteractorImp;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * Presenter for the login view
 */

public class LoginPresenter implements LoginContract.LoginPresenter,LoginInteractor.OnLoginFinished {
    LoginContract.LoginView view;
    private LoginInteractorImp loginInteractorImp;
    private LoginContract.RecoveryView recView;

    public LoginPresenter(LoginContract.LoginView view){
        this.view = view;
        this.loginInteractorImp = new LoginInteractorImp(this);
    }

    public LoginPresenter(LoginContract.RecoveryView recView) {
        this.recView = recView;
        this.loginInteractorImp = new LoginInteractorImp(this);

    }

    @Override
    public void validateCredentials(String email, String pass) {
        loginInteractorImp.validateCredentials(email,pass);

    }

    @Override
    public void sendEmail(String email) {
        loginInteractorImp.sendEmail(email);
    }

    @Override
    public void onDestroy() {
        this.view = null;
        this.loginInteractorImp = null;
    }

    @Override
    public void onEmptyEmail() {
        view.setEmptyEmail();
    }

    @Override
    public void onSigninError() {
        view.setSigninError();
    }

    @Override
    public void onEmptyPass() {
        view.setEmptyPass();
    }



    @Override
    public void onSucces(String email) {
        view.goMainActivity(email);
    }



    @Override
    public void openDialog() {
        recView.openDialog();
    }

    @Override
    public void closeDialog(boolean res) {
        recView.closeDialog(res);
    }

    @Override
    public void openDialogLogin() {
        view.openDialog();
    }

    @Override
    public void closeDialogLogin() {
        view.closeDialog();
    }


}
