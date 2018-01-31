package com.example.linux.muscleapp.ui.session.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.ui.session.interactor.MainInteractor;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public class MainInteractorImp implements MainInteractor {
    private  onLoadFinish onLoadFinish;
    private LoadAsyncTask loadAsyncTask;

    public MainInteractorImp(MainInteractor.onLoadFinish onLoadFinish) {
        this.onLoadFinish = onLoadFinish;
    }

    @Override
    public void getSessions() {
        loadAsyncTask = new LoadAsyncTask();
        loadAsyncTask.execute();
    }

    @Override
    public void getCurrentUser() {
        onLoadFinish.giveCurrentUser(UsersRepository.getInstance().getCurrentUser());
    }

    @Override
    public void getCurrentUser(String email) {
        onLoadFinish.giveCurrentUser(UsersRepository.getInstance().getCurrentUser(email));
    }

    class LoadAsyncTask extends AsyncTask<Void,Void,ArrayList<Session>>{
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            onLoadFinish.openProgress();
        }

        @Override
        protected ArrayList<Session> doInBackground(Void... voids) {
            return SessionsRepository.getInstace().getSessions();
        }

        @Override
        protected void onPostExecute(ArrayList<Session> sessions) {
            super.onPostExecute(sessions);
            onLoadFinish.giveSessions(sessions);
            onLoadFinish.closeProgress();
        }
    }
}
