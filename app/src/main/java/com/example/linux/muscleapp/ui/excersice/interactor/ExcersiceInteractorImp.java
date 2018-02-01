package com.example.linux.muscleapp.ui.excersice.interactor;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.repositories.ExcersiceRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

/**
 * Created by linux on 3/12/17.
 */

public class ExcersiceInteractorImp implements ExcersiceInteractor {

    private OnExcersiceFinish onExcersiceFinish;

    public ExcersiceInteractorImp(OnExcersiceFinish onExcersiceFinish) {
        this.onExcersiceFinish = onExcersiceFinish;
    }

    @Override
    public void addExcersice(Excersice excersice) {
        if(excersice.getName().isEmpty())
            onExcersiceFinish.onEmptyName();
        else if(excersice.getMuscle().isEmpty())
            onExcersiceFinish.onEmptyMuscle();
        else{
            SessionTmpDates.addExcersice(excersice);
            onExcersiceFinish.onSuccess(excersice.getId());
        }
    }
}
