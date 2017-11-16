package com.example.linux.muscleapp.ui.session;

import com.example.linux.muscleapp.data.db.repositories.SessionsRepository;

/**
 * Created by linux on 16/11/17.
 */

public class MainInteractorImp implements MainInteractor {

    @Override
    public void getSessions(onLoadFinish onLoadFinish) {
        onLoadFinish.giveSessions(SessionsRepository.getInstace().getSessions());
    }
}
