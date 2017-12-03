package com.example.linux.muscleapp.ui.session.contract;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
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

         interface Comment {
             void getCommentNumbers(int idSession);
         }

     }

    interface MainPresenter {
         void getSessions();
         void getCurrentUser();
         void onDestroy();

         }

    interface AddSessionView{
         void fillExcersices(ArrayList<Excersice> excersices);
    }

    interface AddSessionPresenter{
         void getExcersices();
         void deleteExcersice(int position);
         void onDestroy();
    }

}
