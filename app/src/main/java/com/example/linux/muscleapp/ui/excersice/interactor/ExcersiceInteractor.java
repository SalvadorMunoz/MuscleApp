package com.example.linux.muscleapp.ui.excersice.interactor;

/**
 * Created by linux on 3/12/17.
 */

public interface ExcersiceInteractor {
    void addExcersice(int id, int session, String name, String muscle, String url, String type,int series, int repetitions, int time,OnExcersiceFinish onExcersiceFinish);

    interface OnExcersiceFinish{
        void onEmptyName();
        void onEmptyMuscle();
        void onSuccess(int id);
    }
}
