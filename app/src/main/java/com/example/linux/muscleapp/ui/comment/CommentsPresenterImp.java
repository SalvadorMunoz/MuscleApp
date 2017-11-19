package com.example.linux.muscleapp.ui.comment;

import com.example.linux.muscleapp.data.db.pojo.Commentary;

import java.util.ArrayList;

/**
 * Created by linux on 18/11/17.
 */

public class CommentsPresenterImp implements CommentsPresenter,CommentsInteractor.OnLoadFinish,CommentsInteractor.OnCommentAdded{
    CommentsView view ;
    CommentsInteractor interactor;
    public  CommentsPresenterImp(CommentsView view){
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

    @Override
    public void onCommentAdded() {
        view.updateComments();
    }
}
