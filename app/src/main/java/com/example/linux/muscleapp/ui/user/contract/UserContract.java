package com.example.linux.muscleapp.ui.user.contract;

import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 26/05/18.
 */

public interface UserContract {
    interface View{
        void openDialog();
        void fillUsers(ArrayList<User> users);
        void closeDialog();
    }

    interface Presenter{
        void getUsers();
        void getFilteredUsers(String name);
    }
}
