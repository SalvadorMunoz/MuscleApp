package com.example.linux.muscleapp.ui.dates.contract;

import com.example.linux.muscleapp.data.db.pojo.SessionDate;

import java.util.ArrayList;

/**
 * Created by linux on 2/12/17.
 */

public interface AddDateContract {
    interface View{
        void loadDates(ArrayList<SessionDate> dates);
    }

    interface Presenter{
        void loadDates();
        void addDate(SessionDate sessionDate);
        void deleteDate(int position);
        void onDestroy();
    }
}
