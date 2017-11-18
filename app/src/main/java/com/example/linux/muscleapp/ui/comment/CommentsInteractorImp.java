package com.example.linux.muscleapp.ui.comment;

import com.example.linux.muscleapp.data.db.repositories.CommentsRepository;

/**
 * Created by linux on 18/11/17.
 */

public class CommentsInteractorImp implements CommentsInteractor {


    @Override
    public void fillComments(int resource, OnLoadFinish onLoadFinish) {
        onLoadFinish.onLoadFinish(CommentsRepository.getInstace().getComments(resource));
    }
}
