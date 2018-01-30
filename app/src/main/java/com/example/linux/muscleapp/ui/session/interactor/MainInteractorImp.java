package com.example.linux.muscleapp.ui.session.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.ui.session.interactor.MainInteractor;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public class MainInteractorImp implements MainInteractor {

    @Override
    public void getSessions(onLoadFinish onLoadFinish) {
        onLoadFinish.giveSessions(SessionsRepository.getInstace().getSessions());
    }

    @Override
    public void getCurrentUser(onLoadFinish onLoadFinish) {
        onLoadFinish.giveCurrentUser(UsersRepository.getInstance().getCurrentUser());
    }

    @Override
    public void getCurrentUser(String email, onLoadFinish onLoadFinish) {
        onLoadFinish.giveCurrentUser(UsersRepository.getInstance().getCurrentUser(email));
    }

}
