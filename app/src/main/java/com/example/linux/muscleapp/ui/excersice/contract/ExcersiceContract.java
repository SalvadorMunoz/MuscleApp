package com.example.linux.muscleapp.ui.excersice.contract;

/**
 * Created by linux on 3/12/17.
 */

public interface ExcersiceContract {
    interface AddExcersiceView{
        void setOnEmptyName();
        void setOnEmptyMuscle();
        void onSuccess();
    }
    interface AddExcersicePresenter{
        void addExcersice(int id, int session, String name, String muscle, String url, String type,int series, int repetitions, int time);

        void onDestroy();
    }
}
