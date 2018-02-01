package com.example.linux.muscleapp.ui.excersice.presenter;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
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
        interactor = new ExcersiceInteractorImp(this);
    }
    @Override
    public void addExcersice(Excersice excersice) {
        interactor.addExcersice(excersice);
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
    public void onSuccess(int id) {
        view.onSuccess(id);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }


}
