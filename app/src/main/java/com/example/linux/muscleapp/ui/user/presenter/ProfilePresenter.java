package com.example.linux.muscleapp.ui.user.presenter;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.ui.user.contract.ProfileContract;
import com.example.linux.muscleapp.ui.user.interactor.ProfileInteractor;
import com.example.linux.muscleapp.ui.user.interactor.ProfileInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 30/05/18.
 */

public class ProfilePresenter implements ProfileContract.Presenter ,ProfileInteractor.OnUsersSessionLoad{
    private ProfileContract.View view;
    private ProfileInteractorImp interactor;

    public ProfilePresenter(ProfileContract.View view) {
        this.view = view;
        this.interactor = new ProfileInteractorImp(this);
    }

    @Override
    public void getSessions(int id) {
        interactor.getSessions(id);
    }

    @Override
    public void setFavourite(int session, int user) {
        interactor.setFavourite(session,user);
    }

    @Override
    public void deleteFavourite(int session, int user) {
        interactor.deleteFavourite(session,user);
    }

    @Override
<<<<<<< HEAD
    public void deleteSession(int session) {
        interactor.deleteSession(session);
=======
    public void deleteSession(int id) {
        interactor.deleteSession(id);
>>>>>>> HEAD@{2}
    }

    @Override
    public void fillSessions(ArrayList<Session> sessions, ArrayList<Boolean> favourites, ArrayList<User> usernames) {
        view.fillSessions(sessions,favourites,usernames);
    }

    @Override
<<<<<<< HEAD
    public void removeFromList(int current) {
        view.removeFromList(current);
=======
    public void removeFromList(int id) {
        view.removeFromList(id);
>>>>>>> HEAD@{2}
    }
}
