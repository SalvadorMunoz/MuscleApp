package com.example.linux.muscleapp.ui.favourite.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.FavouriteRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;

import java.util.ArrayList;

/**
 * Created by linux on 23/05/18.
 */

public class FavouritesInteractorImp implements FavouritesInteractor {
    private OnLoadFavourites onLoadFavourites;

    public FavouritesInteractorImp(OnLoadFavourites onLoadFavourites) {
        this.onLoadFavourites = onLoadFavourites;
    }

    @Override
    public void getFavourites(int currentUser) {
        new FavouritesTask().execute(currentUser);
    }

    @Override
    public void deleteFavourite(Favourite current) {
        new DeleteTask().execute(current);
    }

    private class FavouritesTask extends AsyncTask<Integer,Void,ArrayList<Session>>{
        ArrayList<User> usernames;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onLoadFavourites.openDialog();

        }


        @Override
        protected ArrayList<Session> doInBackground(Integer... integers) {
            usernames = UsersRepository.getInstance().getNameFronId();
            return FavouriteRepository.getInstace().getFavourites(integers[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Session> sessions) {
            super.onPostExecute(sessions);
            onLoadFavourites.fillFavourites(sessions,usernames);
            onLoadFavourites.closeDialog();
        }
    }

    private class DeleteTask extends AsyncTask<Favourite,Void,Integer>{

        @Override
        protected Integer doInBackground(Favourite... favourites) {
            FavouriteRepository.getInstace().delete(favourites[0]);
            return favourites[0].getSession();
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            onLoadFavourites.removeFromList(integer);
        }
    }
}
