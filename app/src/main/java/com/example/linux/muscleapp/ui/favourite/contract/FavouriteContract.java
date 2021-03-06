package com.example.linux.muscleapp.ui.favourite.contract;

import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 13/05/18.
 */

public interface FavouriteContract {
    interface Presenter{
        void getFavourites(int currentUser);
        void deleteFavourite(Favourite current);
    }

    interface View{
        void openDialog();
        void fillFavourites(ArrayList<Session> favourites, ArrayList<User> usernames);
        void removeFromList(int session);
        void closeDialog();
    }



}
