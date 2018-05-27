package com.example.linux.muscleapp.ui.user.presenter;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.user.contract.UserContract;
import com.example.linux.muscleapp.ui.user.interactor.UserInteractor;
import com.example.linux.muscleapp.ui.user.interactor.UserInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 26/05/18.
 */

public class UserPresenter implements UserContract.Presenter,UserInteractor.OnUsersLoad {
    private UserContract.View view;
    private UserInteractorImp interactor;

    public UserPresenter(UserContract.View view) {
        this.view = view;
        interactor = new UserInteractorImp(this);
    }

    @Override
    public void openDialog() {
        view.openDialog();
    }

    @Override
    public void fillUsers(ArrayList<User> users) {
        view.fillUsers(users);
    }

    @Override
    public void closeDialog() {
        view.closeDialog();
    }

    @Override
    public void getUsers() {
        interactor.getUsers();
    }

    @Override
    public void getFilteredUsers(String name) {
        interactor.filterUsers(name);
    }
}
