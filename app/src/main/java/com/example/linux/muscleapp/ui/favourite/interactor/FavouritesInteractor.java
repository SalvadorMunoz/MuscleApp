package com.example.linux.muscleapp.ui.favourite.interactor;

import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 23/05/18.
 */

public interface FavouritesInteractor {
    void getFavourites(int currentUser);
    void deleteFavourite(Favourite current);
    interface OnLoadFavourites{
        void openDialog();
        void closeDialog();
        void fillFavourites(ArrayList<Session> favourites, ArrayList<User> usernames);
        void removeFromList(int session);
    }
}
