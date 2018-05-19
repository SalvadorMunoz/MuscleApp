package com.example.linux.muscleapp.ui.session.interactor;

import com.example.linux.muscleapp.data.db.pojo.User;

/**
 * Created by linux on 19/05/18.
 */

public interface MainInteractor {
    void getCurrentUser(String email);
    interface OnUserLoad{
        void setCurrentUser(User currentUser);
    }
}
