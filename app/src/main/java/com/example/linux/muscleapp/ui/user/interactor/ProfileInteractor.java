package com.example.linux.muscleapp.ui.user.interactor;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 30/05/18.
 */

public interface ProfileInteractor {
    void getSessions(int id);
    void setFavourite(int session, int user);
    void deleteFavourite(int session, int user);
    void deleteSession(int session);

    interface OnUsersSessionLoad {
        void fillSessions(ArrayList<Session> sessions, ArrayList<Boolean> favourites, ArrayList<User> usernames);

        void removeFromList(int current);

        void deleteSession(int id);
    }


}
