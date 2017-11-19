package com.example.linux.muscleapp.ui.session;

import com.example.linux.muscleapp.data.db.pojo.Session;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public interface MainPresenter {
    void getSessions();
    void getCurrentUser();
    void onDestroy();


}
