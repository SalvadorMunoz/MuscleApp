package com.example.linux.muscleapp.ui.session.presenter;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.session.contract.MainContract;
import com.example.linux.muscleapp.ui.session.interactor.MainInteractorImp;

/**
 * Created by linux on 19/05/18.
 */

public class MainPresenter implements MainContract.Presenter,MainInteractorImp.OnUserLoad{
    private  MainContract.View view;
    private  MainInteractorImp interactor;

    public MainPresenter(MainContract.View view) {
        this.view = view;
        interactor = new MainInteractorImp(this);
    }

    @Override
    public void getCurrentUser(String name) {
        interactor.getCurrentUser(name);
    }

    @Override
    public void setCurrentUser(User currentUser) {
        view.setCurrentUser(currentUser);
    }
}
