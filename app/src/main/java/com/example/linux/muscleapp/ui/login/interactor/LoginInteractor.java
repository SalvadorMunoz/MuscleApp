package com.example.linux.muscleapp.ui.login.interactor;

/**
 * Created by linux on 14/11/17.
 */

public interface LoginInteractor {
    void validateCredentials(String email, String pass);
    void sendEmail(String email);
    interface  OnLoginFinished{
        void onEmptyEmail();
        void onSigninError();
        void onEmptyPass();
        void onSucces(String email);
        void openDialog();
        void closeDialog(boolean res);
        void openDialogLogin();
        void closeDialogLogin();
    }
}
