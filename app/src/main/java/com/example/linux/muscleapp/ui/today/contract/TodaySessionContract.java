package com.example.linux.muscleapp.ui.today.contract;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 26/05/18.
 */

public interface TodaySessionContract {
    interface View{
        void openDialog();
        void fillSessions(ArrayList<Session> today,ArrayList<User> usernames,ArrayList<Boolean> favourites);
        void closeDialog();
    }

    interface Presenter{
        void getTodaySession(int id);
        void deleteFavourite(int session,int follower);
        void setFavourite(int session,int follower);
    }

}
