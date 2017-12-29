package com.example.linux.muscleapp.ui.session.presenter;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;
import com.example.linux.muscleapp.ui.session.interactor.AddSessionInteractor;
import com.example.linux.muscleapp.ui.session.interactor.AddSessionInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 3/12/17.
 */

public class AddSessionListExcersicesPresenter implements SessionContract.AddSessionPresenter,AddSessionInteractor.OnAddSessionFinish{

    private  SessionContract.AddSessionView view;
    private AddSessionInteractorImp interactor;
    public AddSessionListExcersicesPresenter(SessionContract.AddSessionView view){
        this.view = view;
        this.interactor = new AddSessionInteractorImp();
    }

    @Override
    public void addSession(String name, String pass, String date, int user) {
        interactor.addSession(name,pass,date,user,this);
    }

    @Override
    public void getExcersices() {
        interactor.getExcersices(this);
    }

    @Override
    public void deleteExcersice(int position) {
        interactor.deleteExcersice(position, this);
    }

    @Override
    public void onDestroy() {
        view = null;
        interactor = null;
    }

    @Override
    public void onLoadExcersices(ArrayList<Excersice> excersices) {
        view.fillExcersices(excersices);
    }

    @Override
    public void onSuccess() {
        view.goBack();
    }

    @Override
    public void onEmptyName() {
        view.setEmptyName();
    }
}
