package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.pojo.Excersice;

import java.util.ArrayList;

/**
 * Created by linux on 11/01/18.
 */

public interface SeeSessionInteractor {
    void getExcersices(int sessionId);
    interface OnSessionSeen{
        void onSuccess(ArrayList<Excersice>excersices);
    }
}
