package com.example.linux.muscleapp.ui.user.interactor;

import android.app.IntentService;
import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.FavouriteRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;

import java.util.ArrayList;

/**
 * Created by linux on 30/05/18.
 */

public class ProfileInteractorImp implements ProfileInteractor {
    private OnUsersSessionLoad onUsersSessionLoad;
    private boolean isAdding;
    private int session;

    public ProfileInteractorImp(OnUsersSessionLoad onUsersSessionLoad) {
        this.onUsersSessionLoad = onUsersSessionLoad;
    }

    @Override
    public void getSessions(int id) {
        new LoadUserSessionTask().execute(id);
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

    @Override
<<<<<<< HEAD
    public void deleteSession(int session) {
        this.session = session;
        new SessionAsyncTask().execute(session);
=======
    public void deleteSession(int id) {
        new SessionAsyncTask().execute(id);
>>>>>>> HEAD@{2}
    }

    private class LoadUserSessionTask extends AsyncTask<Integer,Void,ArrayList<Session>>{
        private  ArrayList<Session> favourites;
        private ArrayList<User> usernames;
        private int user;
        @Override
        protected ArrayList<Session> doInBackground(Integer... integers) {
            user = integers[0];
            favourites = FavouriteRepository.getInstace().getFavourites(integers[0]);
            usernames = UsersRepository.getInstance().getAllUsers();
            return SessionsRepository.getInstace().getUsersSession(integers[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Session> sessions) {
            super.onPostExecute(sessions);
            ArrayList<Boolean> res = new ArrayList<>();
            for(int i = 0; i < sessions.size();i++){
                    if (favourites.contains(sessions.get(i)))
                        res.add(true);
                    else
                        res.add(false);
                }

            onUsersSessionLoad.fillSessions(sessions,res,usernames);
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
    class SessionAsyncTask extends AsyncTask<Integer,Void,Integer>{

        @Override
        protected Integer doInBackground(Integer... integers) {
            SessionsRepository.getInstace().delete(integers[0]);
            return integers[0];
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            onUsersSessionLoad.removeFromList(integer);
        }
    }

    class SessionAsyncTask extends  AsyncTask<Integer,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onUsersSessionLoad.removeFromList(session);
        }

        @Override
        protected Void doInBackground(Integer... integers) {
            SessionsRepository.getInstace().removeSession(integers[0]);
            return null;
        }
    }

}

