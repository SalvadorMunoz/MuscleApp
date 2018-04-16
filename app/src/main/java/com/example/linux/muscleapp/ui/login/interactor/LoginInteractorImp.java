package com.example.linux.muscleapp.ui.login.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;

/**
 * Created by linux on 14/11/17.
 */

public class LoginInteractorImp implements LoginInteractor {
    private OnLoginFinished onLoginFinished;

    public LoginInteractorImp(OnLoginFinished onLoginFinished) {
        this.onLoginFinished = onLoginFinished;
    }

    private ValidateUserTask validateUserTask;

    @Override
    public void validateCredentials(String email, String pass) {
        validateUserTask = new ValidateUserTask();
        if (email.isEmpty())
            onLoginFinished.onEmptyEmail();
        else if (pass.isEmpty())
            onLoginFinished.onEmptyPass();
        else
            validateUserTask.execute(email,pass);
    }


    class ValidateUserTask extends AsyncTask<String,Boolean,Boolean> {
        String email;

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean res = false;
            if(UsersRepository.getInstance().validateCredentials(strings[0],strings[1])) {
                UsersRepository.getInstance().setCurrentUser(strings[0]);
                email = strings[0];
                res = true;
            }

            return res;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            if(aBoolean)
                onLoginFinished.onSucces(email);
            else
                onLoginFinished.onSigninError();
        }
    }
}
