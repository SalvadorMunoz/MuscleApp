package com.example.linux.muscleapp.ui.dates.presenter;

import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.ui.dates.contract.AddDateContract;
import com.example.linux.muscleapp.ui.dates.interactor.AddDateInteractor;
import com.example.linux.muscleapp.ui.dates.interactor.AddDateInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 2/12/17.
 */

public class AddSessionDatePresenter implements AddDateContract.Presenter,AddDateInteractor.OnLoadDatesFinished{
    private AddDateContract.View view;
    private AddDateInteractorImp interactor;

    public AddSessionDatePresenter(AddDateContract.View view){
        this.view = view;
        interactor = new AddDateInteractorImp();
    }
    @Override
    public void loadDates() {
        interactor.loadDates(this);
    }

    @Override
    public void addDate(SessionDate sessionDate) {
        interactor.addDate(sessionDate,this);
    }

    @Override
    public void deleteDate(int position) {
        interactor.deleteDate(position,this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }


    @Override
    public void onSuccess(ArrayList<SessionDate> dates) {
        view.loadDates(dates);
    }
}
