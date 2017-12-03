package com.example.linux.muscleapp.ui.dates.interactor;

import com.example.linux.muscleapp.data.db.pojo.SessionDate;

import java.util.ArrayList;

/**
 * Created by linux on 2/12/17.
 */

public interface AddDateInteractor {
    void loadDates(OnLoadDatesFinished onLoadDatesFinished);
    void addDate(SessionDate sessionDate,OnLoadDatesFinished onLoadDatesFinished);
    void deleteDate(int position,OnLoadDatesFinished onLoadDatesFinished);

    interface  OnLoadDatesFinished{
        void onSuccess(ArrayList<SessionDate> dates);
    }
}
