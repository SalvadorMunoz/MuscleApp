package com.example.linux.muscleapp.ui.signup;

/**
 * Created by linux on 14/11/17.
 */

public interface SignupPresenter {
    void add(String email,String pass,String name, String residence,String date);
    void onDestroy();
}
