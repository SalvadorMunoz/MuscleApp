package com.example.linux.muscleapp.ui.signup;

/**
 * Created by linux on 14/11/17.
 */

class SignupPresenterImp implements SignupPresenter , SignupInteractor.OnSignupFinish{
    SignUpView view;
    SignupInteractorImp interactor;
    public SignupPresenterImp(SignUpView view){
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
        view.goLogin();
    }

    @Override
    public void onEmptyEmail() {
        view.setEmptyEmail();
    }

    @Override
    public void onEmptyPass() {
        view.setEmptyPass();
    }

    @Override
    public void onEmptyName() {
        view.setEmptyName();
    }

    @Override
    public void onEmptyResidence() {
        view.setEmptyResidence();
    }

    @Override
    public void onEmptyBornDate() {
        view.setEmptyBornDate();
    }

    @Override
    public void onEmailExists() {
        view.setEmailExists();
    }
}
