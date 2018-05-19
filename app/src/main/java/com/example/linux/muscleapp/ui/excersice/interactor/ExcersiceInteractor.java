package com.example.linux.muscleapp.ui.excersice.interactor;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.User;

/**
 * Created by linux on 3/12/17.
 */

public interface ExcersiceInteractor {
    void addExcersice(Excersice excersice);
    void getCurrentUser(String email);

    interface OnExcersiceFinish{
        void onEmptyName();
        void onEmptyMuscle();
        void onSuccess(int id);
        void setCurrenUser(User user);
    }
}
