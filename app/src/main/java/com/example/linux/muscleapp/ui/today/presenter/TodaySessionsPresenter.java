package com.example.linux.muscleapp.ui.today.presenter;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.today.contract.TodaySessionContract;
import com.example.linux.muscleapp.ui.today.interactor.TodaySessionInteractor;
import com.example.linux.muscleapp.ui.today.interactor.TodaySessionsInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 26/05/18.
 */

public class TodaySessionsPresenter implements TodaySessionContract.Presenter,TodaySessionInteractor.OnLoadTodaySessions {
    private TodaySessionContract.View view;
    private TodaySessionsInteractorImp interactor;

    public TodaySessionsPresenter(TodaySessionContract.View view) {
        this.view = view;
        interactor = new TodaySessionsInteractorImp(this);
    }

    @Override
    public void getTodaySession(int id) {
        interactor.getTodaySession(id);
    }

    @Override
    public void deleteFavourite(int session, int follower) {
        interactor.deleteFavourite(session,follower);
    }

    @Override
    public void setFavourite(int session, int follower) {
        interactor.setFavourite(session,follower);
    }

    @Override
    public void fllSessions(ArrayList<Session> today, ArrayList<User>usernames,ArrayList<Boolean> favourites) {
        view.fillSessions(today,usernames,favourites);
    }

    @Override
    public void openDialog() {
        view.openDialog();
    }



    @Override
    public void closeDialog() {
        view.closeDialog();

    }
}
