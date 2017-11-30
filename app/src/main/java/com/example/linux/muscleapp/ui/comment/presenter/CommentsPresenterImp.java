package com.example.linux.muscleapp.ui.comment.presenter;

import com.example.linux.muscleapp.data.db.pojo.Commentary;

import com.example.linux.muscleapp.ui.comment.contract.CommentsContract;
import com.example.linux.muscleapp.ui.comment.interactor.CommentsInteractor;
import com.example.linux.muscleapp.ui.comment.interactor.CommentsInteractorImp;

import java.util.ArrayList;

/**
 * Created by linux on 18/11/17.
 */

public class CommentsPresenterImp implements CommentsContract.CommentsPresenter,CommentsInteractor.OnLoadFinish{
    CommentsContract.CommentsView view ;
    CommentsInteractor interactor;
    public  CommentsPresenterImp(CommentsContract.CommentsView view){
        this.view = view;
        interactor = new CommentsInteractorImp();
    }
    @Override
    public void fillComments(int resource) {
        interactor.fillComments(resource,this);

    }

    @Override
    public void addComment(int resource, String user, String message) {
        interactor.addComment(resource,user,message,this);

    }

    @Override
    public void onDestroy() {
        this.view = null;
    }

    @Override
    public void onLoadFinish(ArrayList<Commentary> comments) {
        view.fillComments(comments);
    }

}
