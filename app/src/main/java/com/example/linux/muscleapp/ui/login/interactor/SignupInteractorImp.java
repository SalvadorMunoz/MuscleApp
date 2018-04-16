package com.example.linux.muscleapp.ui.login.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.ui.login.interactor.SignupInteractor;
import com.example.linux.muscleapp.ui.utils.PatternsValidator;

/**
 * Created by linux on 15/11/17.
 */

public class SignupInteractorImp implements SignupInteractor {
    private OnSignupFinish onSignupFinish;
    private  AddUserTask addUserTask;

    public SignupInteractorImp(OnSignupFinish onSignupFinish) {
        this.onSignupFinish = onSignupFinish;
    }

    @Override
    public void add(String email, String pass, String name, String residence, String bornDate) {
        addUserTask = new AddUserTask();
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
        else if(!PatternsValidator.isEmailValid(email))
            onSignupFinish.onErrorEmail();
        else if(!PatternsValidator.isPasswordValid(pass))
            onSignupFinish.onErrorPass();
        else{
            addUserTask.execute(new User(0,email, pass, name,  bornDate,residence, 0,"caca"));
        }
    }

    class AddUserTask extends AsyncTask<User,Boolean,Boolean> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onSignupFinish.openDialog();
        }

        @Override
        protected Boolean doInBackground(User... users) {
            boolean res = true;

            if(UsersRepository.getInstance().userExists(users[0].getEmail()))
                res = false;
            else {
                UsersRepository.getInstance().add(users[0]);
                UsersRepository.getInstance().sendConfirmEmail(users[0].getEmail());
            }
            return res;
        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            if(aBoolean)
                onSignupFinish.onSuccess();
            else
                onSignupFinish.onEmailExists();

            onSignupFinish.closeDialog(aBoolean);

        }
    }
}
