package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public interface MainInteractor{
    void getSessions();
    void getCurrentUser();
    void getCurrentUser(String email);
    void setFavourite(int session, int current);
    interface onLoadFinish{
        void giveSessions(ArrayList<Session> sessions,ArrayList<User>usernames);
        void giveCurrentUser(User user);
        void openProgress();
        void closeProgress();
        void updateFavourites();
    }
}
