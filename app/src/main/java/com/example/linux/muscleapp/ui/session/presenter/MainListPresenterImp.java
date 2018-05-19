package com.example.linux.muscleapp.ui.session.presenter;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.interactor.MainListInteractor;
import com.example.linux.muscleapp.ui.session.interactor.MainListInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public class MainListPresenterImp implements SessionContract.MainPresenter,MainListInteractor.onLoadFinish {
    private SessionContract.MainView view;
    private MainListInteractorImp interactor;

    public MainListPresenterImp(SessionContract.MainView view){
        this.view = view;
        interactor = new MainListInteractorImp(this);
    }



    @Override
    public void getSessions() {
        interactor.getSessions();

    }

    @Override
    public void getCurrentUser(String email) {
        interactor.getCurrentUser(email);
    }

    @Override
    public void getCurrentUser() {

    }


    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void setFavourite(int session, int current) {
        interactor.setFavourite(session, current);
    }


    @Override
    public void giveSessions(ArrayList<Session> sessions,ArrayList<User> usernames) {
        view.fillSessions(sessions,usernames);
    }

    @Override
    public void giveCurrentUser(User user) {
        view.getCurrentUser(user);
    }

    @Override
    public void openProgress() {
        view.openRefreshing();
    }

    @Override
    public void closeProgress() {
        view.closeRefreshing();
    }

    @Override
    public void updateFavourites() {
        view.updateFavourite();
    }


}
