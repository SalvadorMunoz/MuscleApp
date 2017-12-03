package com.example.linux.muscleapp.ui.excersice.interactor;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

/**
 * Created by linux on 3/12/17.
 */

public class ExcersiceInteractorImp implements ExcersiceInteractor {

    @Override
    public void addExcersice(int id, int session, String name, String muscle, String url, String type, int series, int repetitions, int time, OnExcersiceFinish onExcersiceFinish) {
        if(name.isEmpty())
            onExcersiceFinish.onEmptyName();
        else if(muscle.isEmpty())
            onExcersiceFinish.onEmptyMuscle();
        else{
            SessionTmpDates.addExcersice(new Excersice(id,session,name,muscle,url,type,series,repetitions,time));
            onExcersiceFinish.onSuccess();
        }
    }
}
