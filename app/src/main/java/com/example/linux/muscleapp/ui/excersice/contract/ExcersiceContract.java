package com.example.linux.muscleapp.ui.excersice.contract;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.User;

/**
 * Created by linux on 3/12/17.
 */

public interface ExcersiceContract {
    interface AddExcersiceView{
        void setOnEmptyName();
        void setOnEmptyMuscle();
        void onSuccess(int id);
        void setCurrentUser(User user);
    }
    interface AddExcersicePresenter{
        void addExcersice(Excersice excersice);
        void getCurrentUser(String email);
        void onDestroy();
    }
}
