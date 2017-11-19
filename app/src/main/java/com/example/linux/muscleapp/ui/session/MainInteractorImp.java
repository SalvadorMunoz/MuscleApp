package com.example.linux.muscleapp.ui.session;

import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;

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
        User user = UsersRepository.getInstance().getCurrentUser();
        onLoadFinish.giveCurrentUser(UsersRepository.getInstance().getCurrentUser());
    }
}
