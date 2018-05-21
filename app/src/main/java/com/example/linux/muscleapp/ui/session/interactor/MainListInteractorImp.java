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
    private boolean isAdding = true;

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
        isAdding = true;
        new FavouriteAsyncTask().execute(tmp);
    }

    @Override
    public void deleteFavourite(int sessionn, int current) {
        Favourite tmp =  new Favourite(sessionn,current);
        isAdding = false;
        new FavouriteAsyncTask().execute(tmp);
    }

    class LoadAsyncTask extends AsyncTask<Void,Void,ArrayList<Session>>{
        private  ArrayList<User> usernames;
        private ArrayList<Session> tmp;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onLoadFinish.openProgress();
        }

        @Override
        protected ArrayList<Session> doInBackground(Void... voids) {
            usernames = UsersRepository.getInstance().getNameFronId();
              tmp= FavouriteRepository.getInstace().getFavourites(21);
            return SessionsRepository.getInstace().getSessions();
        }

        @Override
        protected void onPostExecute(ArrayList<Session> sessions) {
            super.onPostExecute(sessions);
            ArrayList<Boolean> favourites = new ArrayList<>();
            for(int i = 0; i < sessions.size();i++){
                if(tmp.contains(sessions.get(i)))
                    favourites.add(true);
                else
                    favourites.add(false);
            }
            onLoadFinish.giveSessions(sessions,usernames,favourites,tmp);
            onLoadFinish.closeProgress();
        }
    }
    class FavouriteAsyncTask extends  AsyncTask<Favourite,Void,Void>{

        @Override
        protected Void doInBackground(Favourite... favourites) {
            if(isAdding)
                FavouriteRepository.getInstace().add(favourites[0]);
            else
                FavouriteRepository.getInstace().delete(favourites[0]);
            return null;
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
