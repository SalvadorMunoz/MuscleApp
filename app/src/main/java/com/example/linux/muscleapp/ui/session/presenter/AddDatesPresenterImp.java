package com.example.linux.muscleapp.ui.session.presenter;

import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.interactor.AddDatesInteractor;
import com.example.linux.muscleapp.ui.session.interactor.AddDatesInteractorImp;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by linux on 4/02/18.
 */

public class AddDatesPresenterImp implements SessionContract.AddDatesPresenter{
    private AddDatesInteractorImp interactor;

    public AddDatesPresenterImp() {
        this.interactor = new AddDatesInteractorImp();
    }

    @Override
    public void addDate(ArrayList<Calendar> dates) {
        interactor.addDates(dates);
    }

    @Override
    public void onDestroy() {
        interactor = null;
    }
}
