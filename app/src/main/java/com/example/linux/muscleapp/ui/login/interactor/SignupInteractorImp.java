package com.example.linux.muscleapp.ui.login.interactor;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.ui.login.interactor.SignupInteractor;

/**
 * Created by linux on 15/11/17.
 */

public class SignupInteractorImp implements SignupInteractor {
    @Override
    public void add(String email, String pass, String name, String residence, String bornDate, OnSignupFinish onSignupFinish) {
        if(email.isEmpty())
            onSignupFinish.onEmptyEmail();
        else if (pass.isEmpty())
            onSignupFinish.onEmptyPass();
        else if (name.isEmpty())
            onSignupFinish.onEmptyName();
        else if(residence.isEmpty())
            onSignupFinish.onEmptyResidence();
        else if (bornDate.isEmpty())
            onSignupFinish.onEmptyBornDate();
        else if(UsersRepository.getInstance().userExists(email))
            onSignupFinish.onEmailExists();
        else {
            UsersRepository.getInstance().add(new User(email, name, pass, residence, bornDate));
            onSignupFinish.onSuccess();
        }
    }
}
