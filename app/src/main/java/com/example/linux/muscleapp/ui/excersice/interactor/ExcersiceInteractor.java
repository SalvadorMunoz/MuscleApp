package com.example.linux.muscleapp.ui.excersice.interactor;

import com.example.linux.muscleapp.data.db.pojo.Excersice;

/**
 * Created by linux on 3/12/17.
 */

public interface ExcersiceInteractor {
    void addExcersice(Excersice excersice);

    interface OnExcersiceFinish{
        void onEmptyName();
        void onEmptyMuscle();
        void onSuccess(int id);
    }
}
