package com.example.linux.muscleapp.ui.user.contract;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 30/05/18.
 */

public interface ProfileContract {
    interface View{
        void fillSessions(ArrayList<Session> sessions, ArrayList<Boolean> favourites, ArrayList<User> usernames);
    }

    interface Presenter{
        void getSessions(int id);
        void setFavourite(int session, int user);
        void deleteFavourite(int session, int user);
    }
}
