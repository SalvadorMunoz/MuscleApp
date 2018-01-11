package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.pojo.SessionDate;

import java.util.ArrayList;

/**
 * Created by linux on 11/01/18.
 */

public interface SeeDatesInteractor {
    void fillDates(int session, OnDatesSeen onDatesSeen);
    interface OnDatesSeen{
        void onSuccess(ArrayList<SessionDate> dates);
    }
}
