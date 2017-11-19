package com.example.linux.muscleapp.ui.session;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 16/11/17.
 */

public interface MainView {
    void fillSessions(ArrayList<Session> sessions);
    void getCurrentUser(User user);
}
