package com.example.linux.muscleapp.ui.session.presenter;

import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.interactor.SeeDatesInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 11/01/18.
 */

public class SeeDatesPresenterImp implements SessionContract.SeeDatesPresenter,SeeDatesInteractorImp.OnDatesSeen{
    private SessionContract.SeeDatesView view;
    private SeeDatesInteractorImp interactor;

    public SeeDatesPresenterImp(SessionContract.SeeDatesView view) {
        this.view = view;
        interactor = new SeeDatesInteractorImp();
    }

    @Override
    public void onSuccess(ArrayList<SessionDate> dates) {
        view.fillDates(dates);
    }

    @Override
    public void getDates(int sessionId) {
        interactor.fillDates(sessionId,this);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }
}
