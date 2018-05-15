package com.example.linux.muscleapp.ui.session.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.FavouriteRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.ui.session.interactor.MainInteractor;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public class MainInteractorImp implements MainInteractor {
    private  onLoadFinish onLoadFinish;
    private LoadAsyncTask loadAsyncTask;

    public MainInteractorImp(MainInteractor.onLoadFinish onLoadFinish) {
        this.onLoadFinish = onLoadFinish;
    }

    @Override
    public void getSessions() {
        loadAsyncTask = new LoadAsyncTask();
        loadAsyncTask.execute();
    }

    @Override
    public void getCurrentUser() {
        onLoadFinish.giveCurrentUser(UsersRepository.getInstance().getCurrentUser());
    }

    @Override
    public void getCurrentUser(String email) {
        onLoadFinish.giveCurrentUser(UsersRepository.getInstance().getCurrentUser(email));
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
}
