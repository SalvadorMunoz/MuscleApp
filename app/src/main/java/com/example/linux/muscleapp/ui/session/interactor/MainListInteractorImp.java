package com.example.linux.muscleapp.ui.session.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.FavouriteRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public class MainListInteractorImp implements MainListInteractor {
    private  onLoadFinish onLoadFinish;
    private LoadAsyncTask loadAsyncTask;

    public MainListInteractorImp(MainListInteractor.onLoadFinish onLoadFinish) {
        this.onLoadFinish = onLoadFinish;
    }

    @Override
    public void getSessions() {
        loadAsyncTask = new LoadAsyncTask();
        loadAsyncTask.execute();
    }


    @Override
    public void getCurrentUser(String email) {
        new UserAsyncTask().execute(email);
    }

    @Override
    public void setFavourite(int session, int current) {
        Favourite tmp = new Favourite(session,current);
        new FavouriteAsyncTask().execute(tmp);
    }

    class LoadAsyncTask extends AsyncTask<Void,Void,ArrayList<Session>>{
        private  ArrayList<User> usernames;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onLoadFinish.openProgress();
        }

        @Override
        protected ArrayList<Session> doInBackground(Void... voids) {
            usernames = UsersRepository.getInstance().getNameFronId();
            return SessionsRepository.getInstace().getSessions();
        }

        @Override
        protected void onPostExecute(ArrayList<Session> sessions) {
            super.onPostExecute(sessions);
            onLoadFinish.giveSessions(sessions,usernames);
            onLoadFinish.closeProgress();
        }
    }
    class FavouriteAsyncTask extends  AsyncTask<Favourite,Void,Favourite>{

        @Override
        protected Favourite doInBackground(Favourite... favourites) {
            FavouriteRepository.getInstace().add(favourites[0]);
            return favourites[0];
        }
    }

    class UserAsyncTask extends AsyncTask<String,Void,User>{

        @Override
        protected User doInBackground(String... strings) {
            return UsersRepository.getInstance().getCurrentUser(strings[0]);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            onLoadFinish.giveCurrentUser(user);
        }
    }
}
