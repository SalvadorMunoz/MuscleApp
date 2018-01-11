package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.repositories.SessionDatesRepository;
import com.example.linux.muscleapp.ui.session.contract.SessionContract;

/**
 * Created by linux on 11/01/18.
 */

public class SeeDatesInteractorImp implements SeeDatesInteractor {
    @Override
    public void fillDates(int session, OnDatesSeen onDatesSeen) {
        onDatesSeen.onSuccess(SessionDatesRepository.getInstance().getSessionDates(session));
    }
}
