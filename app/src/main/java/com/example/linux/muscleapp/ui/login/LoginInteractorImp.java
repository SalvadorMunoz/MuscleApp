package com.example.linux.muscleapp.ui.login;

import com.example.linux.muscleapp.data.db.repositories.UsersRepository;

/**
 * Created by linux on 14/11/17.
 */

public class LoginInteractorImp implements LoginInteractor{
    @Override
    public void validateCredentials(String email, String pass, OnLoginFinished loginFinished) {
        if(email.isEmpty())
            loginFinished.onEmptyEmail();
        else if(pass.isEmpty())
            loginFinished.onEmptyPass();
        else if(UsersRepository.getInstance().validateCredentials(email,pass)) {
            UsersRepository.getInstance().setCurrentUser(email);
            loginFinished.onSucces();
        }
        else
            loginFinished.onSigninError();
    }
}
