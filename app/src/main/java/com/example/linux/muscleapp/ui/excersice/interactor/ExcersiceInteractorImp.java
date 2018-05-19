package com.example.linux.muscleapp.ui.excersice.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.data.db.repositories.ExcersiceRepository;
import com.example.linux.muscleapp.data.db.repositories.UsersRepository;
import com.example.linux.muscleapp.ui.utils.SessionTmpDates;

/**
 * Created by linux on 3/12/17.
 */

public class ExcersiceInteractorImp implements ExcersiceInteractor {

    private OnExcersiceFinish onExcersiceFinish;

    public ExcersiceInteractorImp(OnExcersiceFinish onExcersiceFinish) {
        this.onExcersiceFinish = onExcersiceFinish;
    }

    @Override
    public void addExcersice(Excersice excersice) {
        if(excersice.getName().isEmpty())
            onExcersiceFinish.onEmptyName();
        else if(excersice.getMuscle().isEmpty())
            onExcersiceFinish.onEmptyMuscle();
        else{
            SessionTmpDates.addExcersice(excersice);
            onExcersiceFinish.onSuccess(excersice.getId());
        }
    }

    @Override
    public void getCurrentUser(String email) {
        new UserTask().execute(email);
    }

    class UserTask extends AsyncTask<String,Void,User>{

        @Override
        protected User doInBackground(String... strings) {
            return UsersRepository.getInstance().getCurrentUser(strings[0]);
        }

        @Override
        protected void onPostExecute(User user) {
            super.onPostExecute(user);
            onExcersiceFinish.setCurrenUser(user);
        }
    }
}
