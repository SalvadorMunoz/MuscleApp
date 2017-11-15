package com.example.linux.muscleapp.ui.login;

/**
 * @author Salvador Muñoz
 * @version 1.0
 * Log in presenter interface
 */

public interface LoginPresenter {
    void validateCredentials(String email, String pass);
    void onDestroy();
}
