package com.example.linux.muscleapp.ui.login;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * Presenter for the login view
 */

public class LoginPresenterImp implements LoginPresenter,LoginInteractor.OnLoginFinished{
    LoginView view;
    private LoginInteractorImp loginInteractorImp;

    public LoginPresenterImp (LoginView view){
        this.view = view;
        this.loginInteractorImp = new LoginInteractorImp();
    }


    @Override
    public void validateCredentials(String email, String pass) {
        loginInteractorImp.validateCredentials(email,pass,this);
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
    public void onSucces() {
        view.goMainActivity();
    }
}
