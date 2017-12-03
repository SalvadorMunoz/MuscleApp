package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

/**
 * Created by linux on 3/12/17.
 */

public class AddSessionInteractorImp implements AddSessionInteractor{
    @Override
    public void getExcersices(OnAddSessionFinish onAddSessionFinish) {
        onAddSessionFinish.onLoadExcersices(SessionTmpDates.getExcersices());
    }

    @Override
    public void deleteExcersice(int position, OnAddSessionFinish onAddSessionFinish) {
        SessionTmpDates.deleteExcersice(position);
        onAddSessionFinish.onLoadExcersices(SessionTmpDates.getExcersices());
    }
}
