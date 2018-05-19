package com.example.linux.muscleapp.ui.session.contract;

import com.example.linux.muscleapp.data.db.pojo.User;

/**
 * Created by linux on 19/05/18.
 */

public interface MainContract {
    interface Presenter{
        void getCurrentUser(String name);
    }

    interface View{
        void setCurrentUser(User currentUser);
    }
}
