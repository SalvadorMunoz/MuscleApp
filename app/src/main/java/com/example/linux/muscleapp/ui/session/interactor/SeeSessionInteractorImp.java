package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.repositories.ExcersiceRepository;

/**
 * Created by linux on 11/01/18.
 */

public class SeeSessionInteractorImp implements SeeSessionInteractor {
    private OnSessionSeen onSessionSeen;

    public SeeSessionInteractorImp(OnSessionSeen onSessionSeen) {
        this.onSessionSeen = onSessionSeen;
    }

    @Override
    public void getExcersices(int sessionId ) {
        onSessionSeen.onSuccess(ExcersiceRepository.getInstance().getExcersices(sessionId));
    }
}
