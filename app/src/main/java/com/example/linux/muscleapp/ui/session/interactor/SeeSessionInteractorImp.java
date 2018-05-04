package com.example.linux.muscleapp.ui.session.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.ExcersiceRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionDatesRepository;

import java.util.ArrayList;

/**
 * Created by linux on 11/01/18.
 */

public class SeeSessionInteractorImp implements SeeSessionInteractor {
    private OnSessionSeen onSessionSeen;

    public SeeSessionInteractorImp(OnSessionSeen onSessionSeen) {
        this.onSessionSeen = onSessionSeen;
    }

    @Override
    public void getExcersices(int sessionId ) {
        new ExcersiceTask().execute(sessionId);
    }

    private class ExcersiceTask extends AsyncTask<Integer,Integer,ArrayList<Excersice>>{
        ArrayList<SessionDate> sessionDates = new ArrayList<>();

        @Override
        protected void onPreExecute() {
            onSessionSeen.openDialog();
        }

        @Override
        protected ArrayList<Excersice> doInBackground(Integer... integers) {
            sessionDates = SessionDatesRepository.getInstance().getSessionDates(integers[0]);
            return ExcersiceRepository.getInstance().getExcersices(integers[0]);
        }

        @Override
        protected void onPostExecute(ArrayList<Excersice> excersices) {
            onSessionSeen.onSuccess(excersices,sessionDates);
            onSessionSeen.closeDialog();
        }
    }
}
