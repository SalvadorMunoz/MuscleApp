package com.example.linux.muscleapp.ui.user.interactor;

import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 26/05/18.
 */

public interface UserInteractor {
    void getUsers();
    void filterUsers(String name);

    interface OnUsersLoad{
        void openDialog();
        void fillUsers(ArrayList<User> users);
        void closeDialog();
    }

}
