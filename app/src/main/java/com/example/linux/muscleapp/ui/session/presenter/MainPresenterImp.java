package com.example.linux.muscleapp.ui.session.presenter;

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
    SessionContract.MainView view;
    MainInteractorImp interactor;

    public MainPresenterImp (SessionContract.MainView view){
        this.view = view;
        interactor = new MainInteractorImp();
    }
    @Override
    public void getSessions() {
        interactor.getSessions(this);
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
}
