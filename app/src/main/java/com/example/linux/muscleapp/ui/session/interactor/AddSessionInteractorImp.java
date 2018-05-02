package com.example.linux.muscleapp.ui.session.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.data.db.repositories.ExcersiceRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionDatesRepository;
import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by linux on 3/12/17.
 */

public class AddSessionInteractorImp implements AddSessionInteractor{
     private OnAddSessionFinish onAddSessionFinish;
     private Session tmp;
     private ExcersicesAsyncTask excersicesAsyncTask;

    public AddSessionInteractorImp(OnAddSessionFinish onAddSessionFinish) {
        this.onAddSessionFinish = onAddSessionFinish;
    }

    @Override
    public void addSession(String name, String pass, int user) {
        if(name.isEmpty())
            onAddSessionFinish.onEmptyName();
        else {
            excersicesAsyncTask = new ExcersicesAsyncTask();
            tmp = new Session(-1, user, name, pass, formatDate(new Date()));
            excersicesAsyncTask.execute(tmp);
        }
    }

    @Override
    public void getExcersices() {
        onAddSessionFinish.onLoadExcersices(SessionTmpDates.getExcersices());
    }

    @Override
    public void deleteExcersice(int position) {
        SessionTmpDates.deleteExcersice(position);
        onAddSessionFinish.onLoadExcersices(SessionTmpDates.getExcersices());
    }
    private String  formatDate(Date date){
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(date);
    }

    class ExcersicesAsyncTask extends AsyncTask<Session,Void,Void>{


        @Override
        protected void onPreExecute() {
            onAddSessionFinish.openDialog();

        }

        @Override
        protected Void doInBackground(Session... sessions) {
            Excersice tmpEx = null;
            SessionDate tmpDat = null;

            SessionsRepository.getInstace().add(sessions[0]);




            int tmpId = SessionsRepository.getInstace().getIdFromSession(tmp);

            for(int i = 0; i < SessionTmpDates.getExcersices().size();i++){
                tmpEx = SessionTmpDates.getExcersices().get(i);
                tmpEx.setSession(tmpId);
                ExcersiceRepository.getInstance().add(tmpEx);
            }

            for(int i = 0; i < SessionTmpDates.getDates().size();i++){
                tmpDat = SessionTmpDates.getDates().get(i);
                tmpDat.setSession(tmpId);
                SessionDatesRepository.getInstance().add(tmpDat);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            onAddSessionFinish.closeDialog();
            onAddSessionFinish.onSuccess();

        }
    }
}
