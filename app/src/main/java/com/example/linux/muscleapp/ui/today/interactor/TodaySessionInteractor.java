package com.example.linux.muscleapp.ui.today.interactor;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 26/05/18.
 */

public interface TodaySessionInteractor {
    void getTodaySession(int id);
    void setFavourite(int session, int user);
    void deleteFavourite(int session,int user);
    interface OnLoadTodaySessions{
        void openDialog();
        void fllSessions(ArrayList<Session> today, ArrayList<User> usernames,ArrayList<Boolean> favourites);
        void closeDialog();
    }
}
