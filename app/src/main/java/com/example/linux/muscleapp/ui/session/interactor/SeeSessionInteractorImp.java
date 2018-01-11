package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.repositories.ExcersiceRepository;

/**
 * Created by linux on 11/01/18.
 */

public class SeeSessionInteractorImp implements SeeSessionInteractor {
    @Override
    public void getExcersices(int sessionId, OnSessionSeen onSessionSeen) {
        onSessionSeen.onSuccess(ExcersiceRepository.getInstance().getExcersices(sessionId));
    }
}
