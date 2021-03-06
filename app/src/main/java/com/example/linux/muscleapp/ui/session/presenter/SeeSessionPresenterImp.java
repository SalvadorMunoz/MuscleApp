package com.example.linux.muscleapp.ui.session.presenter;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.interactor.SeeSessionInteractor;
import com.example.linux.muscleapp.ui.session.interactor.SeeSessionInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 11/01/18.
 */

public class SeeSessionPresenterImp implements SessionContract.SeeSessionsPresenter,SeeSessionInteractor.OnSessionSeen {
    private SessionContract.SeeSessionView view;
    private SeeSessionInteractorImp interactor;

    public SeeSessionPresenterImp(SessionContract.SeeSessionView view) {
        this.view = view;
        interactor = new SeeSessionInteractorImp(this);
    }

    @Override
    public void onSuccess(ArrayList<Excersice> excersices, ArrayList<SessionDate> sessionDates) {
        view.fillExcersices(excersices,sessionDates);

    }

    @Override
    public void openDialog() {
        view.openDialog();
    }

    @Override
    public void closeDialog() {
        view.closeDialog();
    }

    @Override
    public void getExcersices(int sessionId) {
        interactor.getExcersices(sessionId);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }
}
