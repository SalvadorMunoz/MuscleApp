package com.example.linux.muscleapp.ui.comment;

import com.example.linux.muscleapp.data.db.pojo.Commentary;
import com.example.linux.muscleapp.data.db.repositories.CommentsRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by linux on 18/11/17.
 */

public class CommentsInteractorImp implements CommentsInteractor {


    @Override
    public void fillComments(int resource, OnLoadFinish onLoadFinish) {
        onLoadFinish.onLoadFinish(CommentsRepository.getInstace().getComments(resource));
    }

    @Override
    public void addComment(int resource, String user, String message, OnCommentAdded onCommentAdded) {
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        CommentsRepository.getInstace().add(new Commentary(0,resource,user,message,dateFormat.format(date)));
        onCommentAdded.onCommentAdded();

    }
}
