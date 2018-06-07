package com.example.linux.muscleapp.ui.session.contract;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.net.SessionService;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by linux on 29/11/17.
 */

public interface SessionContract {

     interface MainView {
         void fillSessions(ArrayList<Session> sessions,ArrayList<User> usernames,ArrayList<Boolean> favourites,ArrayList<Session> favSessions);
         void setPresenter(SessionContract.MainPresenter presenter);
         void getCurrentUser(User user);
         void openRefreshing();
         void closeRefreshing();
         void updateFavourite();

         interface Comment {
             void getCommentNumbers(int idSession);
         }

     }

    interface MainPresenter {

         void getSessions(int user);
         void getCurrentUser(String email);
         void getCurrentUser();
         void onDestroy();
         void setFavourite(int session, int current);
         void deleteFavourite(int session,int current);
         }

    interface AddSessionView{
         void fillExcersices(ArrayList<Excersice> excersices);
         void setEmptyName();
         void goBack(Session session);
         void openDialog();
         void closeDialog();
    }

    interface AddSessionPresenter{
         void addSession(String name,String pass, int user);
         void getExcersices();

         void deleteExcersice(int position);
         void onDestroy();
    }

    interface AddDatesPresenter{
         void addDate(ArrayList<Calendar> dates);
         void onDestroy();

    }

    interface SeeSessionView{
         void fillExcersices(ArrayList<Excersice> excersices, ArrayList<SessionDate> sessionDates);
         void openDialog();
         void closeDialog();
    }
    interface SeeSessionsPresenter{
        void getExcersices(int sessionId);
        void onDestroy();

    }

    interface FromDeleteView{
         void refreshAfterDelete();
    }


}
