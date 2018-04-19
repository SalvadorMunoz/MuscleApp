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

    @Override
    public void sendEmail(String email) {
        new SendEmailTask().execute(email);
    }


    class ValidateUserTask extends AsyncTask<String,Boolean,Boolean> {
        String email;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onLoginFinished.openDialogLogin();
        }

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
            onLoginFinished.closeDialogLogin();
        }
    }
    class  SendEmailTask extends AsyncTask<String,Boolean,Boolean>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onLoginFinished.openDialog();
        }

        @Override
        protected Boolean doInBackground(String... strings) {
            boolean res = false;
            if(UsersRepository.getInstance().userExists(strings[0])){
                res = true;
                UsersRepository.getInstance().sendRecoveryEmail(strings[0]);
            }
            return res;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            onLoginFinished.closeDialog(aBoolean);
        }
    }
}
