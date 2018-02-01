package com.example.linux.muscleapp.ui.excersice.contract;

import com.example.linux.muscleapp.data.db.pojo.Excersice;

/**
 * Created by linux on 3/12/17.
 */

public interface ExcersiceContract {
    interface AddExcersiceView{
        void setOnEmptyName();
        void setOnEmptyMuscle();
        void onSuccess(int id);
    }
    interface AddExcersicePresenter{
        void addExcersice(Excersice excersice);

        void onDestroy();
    }
}
