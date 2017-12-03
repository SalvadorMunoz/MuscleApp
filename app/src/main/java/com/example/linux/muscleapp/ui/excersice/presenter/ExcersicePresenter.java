package com.example.linux.muscleapp.ui.excersice.presenter;

import com.example.linux.muscleapp.ui.excersice.contract.ExcersiceContract;
import com.example.linux.muscleapp.ui.excersice.interactor.ExcersiceInteractor;
import com.example.linux.muscleapp.ui.excersice.interactor.ExcersiceInteractorImp;

/**
 * Created by linux on 3/12/17.
 */

public class ExcersicePresenter implements ExcersiceContract.AddExcersicePresenter, ExcersiceInteractor.OnExcersiceFinish {
    private ExcersiceContract.AddExcersiceView view;
    private ExcersiceInteractorImp interactor;

    public ExcersicePresenter (ExcersiceContract.AddExcersiceView view){
        this.view = view;
        interactor = new ExcersiceInteractorImp();
    }
    @Override
    public void addExcersice(int id, int session, String name, String muscle, String url, String type, int series, int repetitions, int time) {
        interactor.addExcersice(id,session,name,muscle,url,type,series,repetitions,time, this);
    }

    @Override
    public void onEmptyName() {
        view.setOnEmptyName();
    }

    @Override
    public void onEmptyMuscle() {
        view.setOnEmptyMuscle();
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void onSuccess() {
        view.onSuccess();
    }
}
