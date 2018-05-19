package com.example.linux.muscleapp.ui.session.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;

/**
 * Created by linux on 19/05/18.
 */

public class MainInteractorImp implements MainInteractor {
    private  OnUserLoad onUserLoad;

    public MainInteractorImp(OnUserLoad onUserLoad) {
        this.onUserLoad = onUserLoad;
    }

    @Override
    public void getCurrentUser(String email) {
        new UserTask().execute(email);
    }

    class UserTask extends AsyncTask<String,Void,User>{

        @Override
        protected User doInBackground(String... strings) {

            return UsersRepository.getInstance().getCurrentUser(strings[0]);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            onUserLoad.setCurrentUser(user);
        }
    }
}
