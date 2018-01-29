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
    private  AddUserTask addUserTask;
    @Override
    public void add(String email, String pass, String name, String residence, String bornDate, OnSignupFinish onSignupFinish) {
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
        else if(UsersRepository.getInstance().userExists(email))
            onSignupFinish.onEmailExists();
        else {

            onSignupFinish.onSuccess();
            addUserTask.execute(new User(0,email, name, pass, residence, bornDate, R.drawable.no_photo));
        }
    }

    class AddUserTask extends AsyncTask<User,Void,Void> {

        @Override
        protected Void doInBackground(User... users) {
            UsersRepository.getInstance().add(users[0]);
            return null;
        }
    }
}
