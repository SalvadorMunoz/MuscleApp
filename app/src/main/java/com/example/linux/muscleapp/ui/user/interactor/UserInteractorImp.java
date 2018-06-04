package com.example.linux.muscleapp.ui.user.interactor;

import android.media.MediaRouter;
import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;

import java.util.ArrayList;

/**
 * Created by linux on 26/05/18.
 */

public class UserInteractorImp implements UserInteractor {
    private  OnUsersLoad onUsersLoad;
    private  int current;

    public UserInteractorImp(OnUsersLoad onUsersLoad) {
        this.onUsersLoad = onUsersLoad;
    }

    @Override
    public void getUsers(int current) {
        this.current = current;
        new UserAsync().execute("");
    }

    @Override
    public void filterUsers(int current,String name) {
        this.current = current;
        new UserAsync().execute(name);
    }

    private class UserAsync extends AsyncTask<String,Void,ArrayList<User>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onUsersLoad.openDialog();
        }

        @Override
        protected ArrayList<User> doInBackground(String... strings) {
            if(strings[0].isEmpty())
                return UsersRepository.getInstance().getNameFronId(current);
            else
                return UsersRepository.getInstance().getFilteredUsers(new User(current,null,null,strings[0],null,null,-1,null));
        }

        @Override
        protected void onPostExecute(ArrayList<User> users) {
            super.onPostExecute(users);
            onUsersLoad.fillUsers(users);
            onUsersLoad.closeDialog();
        }
    }
}
