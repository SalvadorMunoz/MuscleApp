package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by linux on 4/02/18.
 */

public class AddDatesInteractorImp implements AddDatesInteractor {
    SessionDate sessionDate;
    @Override
    public void addDates(ArrayList<Calendar> dates) {
        for (int i =0; i < dates.size(); i++) {
             sessionDate= new SessionDate(-1,dates.get(i).get(Calendar.DAY_OF_MONTH),dates.get(i).get(Calendar.MONTH)+1,dates.get(i).get(Calendar.YEAR),-1);
            SessionTmpDates.addDate(sessionDate);
        }

    }
}
