package com.example.linux.muscleapp.ui.dates.interactor;

import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

/**
 * Created by linux on 2/12/17.
 */

public class AddDateInteractorImp implements AddDateInteractor {
    @Override
    public void loadDates(OnLoadDatesFinished onLoadDatesFinished) {
        onLoadDatesFinished.onSuccess(SessionTmpDates.getDates());
    }

    @Override
    public void addDate(SessionDate sessionDate,OnLoadDatesFinished onLoadDatesFinished) {
        onLoadDatesFinished.onSuccess(SessionTmpDates.getDates());
    }

    @Override
    public void deleteDate(int position, OnLoadDatesFinished onLoadDatesFinished) {
        SessionTmpDates.deleteDate(position);
        onLoadDatesFinished.onSuccess(SessionTmpDates.getDates());
    }
}
