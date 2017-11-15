package com.example.linux.muscleapp.ui.login;

/**
 * @author Salvador Muñoz
 * @version 1.0
 * Log in view interface
 */

public interface LoginView {
    void goMainActivity();
    void setEmptyEmail();
    void setEmptyPass();
    void setSigninError();
}
