package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.pojo.Excersice;

import java.util.ArrayList;

/**
 * Created by linux on 3/12/17.
 */

public interface AddSessionInteractor {
    void getExcersices(OnAddSessionFinish onAddSessionFinish);
    void deleteExcersice(int position, OnAddSessionFinish onAddSessionFinish);
    interface OnAddSessionFinish{
        void onLoadExcersices(ArrayList<Excersice> excersices);
    }
}
