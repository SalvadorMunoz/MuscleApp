package com.example.linux.muscleapp.ui.login.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;

/**
 * Created by linux on 14/11/17.
 */

public class LoginInteractorImp implements LoginInteractor {
    private ValidateUserTask validateUserTask ;
    @Override
    public void validateCredentials(String email, String pass, OnLoginFinished loginFinished) {
        validateUserTask = new ValidateUserTask();
        if(email.isEmpty())
            loginFinished.onEmptyEmail();
        else if(pass.isEmpty())
            loginFinished.onEmptyPass();
        else if(UsersRepository.getInstance().validateCredentials(email,pass)) {
            validateUserTask.execute(email);
            loginFinished.onSucces();
        }
        else
            loginFinished.onSigninError();
    }

    class ValidateUserTask extends AsyncTask<String,Void,Void> {


        @Override
        protected Void doInBackground(String... strings) {
            UsersRepository.getInstance().setCurrentUser(strings[0]);

            return null;
        }
    }
}
