package com.example.linux.muscleapp.ui.session;

import com.example.linux.muscleapp.data.db.pojo.Session;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public class MainPresenterImp implements MainPresenter,MainInteractor.onLoadFinish {
    MainView view;
    MainInteractorImp interactor;

    public MainPresenterImp (MainView view){
        this.view = view;
        interactor = new MainInteractorImp();
    }
    @Override
    public void getSessions() {
        interactor.getSessions(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void giveSessions(ArrayList<Session> sessions) {
        view.fillSessions(sessions);
    }
}
