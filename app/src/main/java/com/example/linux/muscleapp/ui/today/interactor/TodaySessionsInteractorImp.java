package com.example.linux.muscleapp.ui.today.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.repositories.FavouriteRepository;
import com.example.linux.muscleapp.data.db.repositories.TodaySessionRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.ui.session.interactor.MainListInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 26/05/18.
 */

public class TodaySessionsInteractorImp implements TodaySessionInteractor{
    private OnLoadTodaySessions onLoadTodaySessions;
    private boolean isAdding ;

    public TodaySessionsInteractorImp(OnLoadTodaySessions onLoadTodaySessions) {
        this.onLoadTodaySessions = onLoadTodaySessions;
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
    public void getTodaySession(int id) {
        new TodayAsync().execute(id);
    }

    private class TodayAsync extends AsyncTask<Integer,Void,ArrayList<Session>>{
        ArrayList usernames;
        ArrayList favourites;
        @Override
        protected ArrayList<Session> doInBackground(Integer... integers) {
            usernames = UsersRepository.getInstance().getAllUsers();
            favourites= FavouriteRepository.getInstace().getFavourites(integers[0]);

            return TodaySessionRepository.getInstance().getTodaySessions(integers[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Session> sessions) {
            super.onPostExecute(sessions);
            ArrayList<Boolean> res = new ArrayList<>();
            for(int i = 0; i < sessions.size();i++){
                if(favourites.contains(sessions.get(i)))
                    res.add(true);
                else
                    res.add(false);
            }
            onLoadTodaySessions.fllSessions(sessions,usernames,res);
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
}
