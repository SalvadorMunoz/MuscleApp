package com.example.linux.muscleapp.ui.session.presenter;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.interactor.MainInteractor;
import com.example.linux.muscleapp.ui.session.interactor.MainInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public class MainPresenterImp implements SessionContract.MainPresenter,MainInteractor.onLoadFinish {
    private SessionContract.MainView view;
    private MainInteractorImp interactor;
    private LoadAsyncTask loadAsyncTask;

    public MainPresenterImp (SessionContract.MainView view){
        this.view = view;
        interactor = new MainInteractorImp();
    }



    @Override
    public void getSessions() {
        loadAsyncTask = new LoadAsyncTask();
        loadAsyncTask.execute();
    }

    @Override
    public void getCurrentUser(String email) {
        interactor.getCurrentUser(email,this);
    }

    @Override
    public void getCurrentUser() {
        interactor.getCurrentUser(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void giveSessions(ArrayList<Session> sessions) {
        view.fillSessions(sessions);
    }

    @Override
    public void giveCurrentUser(User user) {
        view.getCurrentUser(user);
    }

    class LoadAsyncTask extends AsyncTask<Void,Void,Void>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            view.openRefreshing();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            interactor.getSessions(MainPresenterImp.this);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            view.closeRefreshing();
        }
    }
}
