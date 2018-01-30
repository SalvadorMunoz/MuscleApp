package com.example.linux.muscleapp.ui.session.contract;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 29/11/17.
 */

public interface SessionContract {

     interface MainView {
         void fillSessions(ArrayList<Session> sessions);
         void setPresenter(SessionContract.MainPresenter presenter);
         void getCurrentUser(User user);
         void openRefreshing();
         void closeRefreshing();


         interface Comment {
             void getCommentNumbers(int idSession);
         }

     }

    interface MainPresenter {

         void getSessions();
         void getCurrentUser(String email);
         void getCurrentUser();
         void onDestroy();

         }

    interface AddSessionView{
         void fillExcersices(ArrayList<Excersice> excersices);
         void setEmptyName();
         void goBack();
    }

    interface AddSessionPresenter{
         void addSession(String name,String pass, int user);
         void getExcersices();

         void deleteExcersice(int position);
         void onDestroy();
    }

    interface SeeSessionView{
         void fillExcersices(ArrayList<Excersice> excersices);
    }
    interface SeeSessionsPresenter{
        void getExcersices(int sessionId);
        void onDestroy();

    }

    interface SeeDatesView{
        void fillDates(ArrayList<SessionDate> dates);
    }
    interface SeeDatesPresenter{
        void getDates(int sessionId);
        void onDestroy();

    }
}
