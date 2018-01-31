package com.example.linux.muscleapp.ui.comment.interactor;

import android.os.AsyncTask;

import com.example.linux.muscleapp.data.db.pojo.Commentary;
import com.example.linux.muscleapp.data.db.repositories.CommentsRepository;
import com.example.linux.muscleapp.ui.comment.interactor.CommentsInteractor;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by linux on 18/11/17.
 */

public class CommentsInteractorImp implements CommentsInteractor {

    private  OnLoadFinish onLoadFinish;

    public CommentsInteractorImp(OnLoadFinish onLoadFinish) {
        this.onLoadFinish = onLoadFinish;
    }

    @Override
    public void fillComments(int resource) {
        onLoadFinish.onLoadFinish(CommentsRepository.getInstace().getComments(resource));
    }

    @Override
    public void addComment(int resource, int user, String message) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date();
        CommentsRepository.getInstace().add(new Commentary(1,resource,user,message,dateFormat.format(date)));
        onLoadFinish.onLoadFinish(CommentsRepository.getInstace().getComments(resource));


    }


}
