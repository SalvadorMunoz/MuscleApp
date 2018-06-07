package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public interface MainListInteractor {
    void getSessions(int user);
    void getCurrentUser(String email);
    void setFavourite(int session, int current);
    void deleteFavourite(int sessionn, int current);
    interface onLoadFinish{
        void giveSessions(ArrayList<Session> sessions,ArrayList<User>usernames,ArrayList<Boolean> favourites,ArrayList<Session>favSessions);
        void giveCurrentUser(User user);
        void openProgress();
        void closeProgress();
        void updateFavourites();
    }
}
