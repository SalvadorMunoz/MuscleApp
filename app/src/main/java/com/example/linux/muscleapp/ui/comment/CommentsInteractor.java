package com.example.linux.muscleapp.ui.comment;

import com.example.linux.muscleapp.data.db.pojo.Commentary;

import java.util.ArrayList;

/**
 * Created by linux on 18/11/17.
 */

public interface CommentsInteractor {
    void fillComments(int resource, OnLoadFinish onLoadFinish);
    void addComment(int resource,String user,String message, OnCommentAdded onCommentAdded);
    interface OnLoadFinish{
        void onLoadFinish(ArrayList<Commentary> comments);
    }
    interface  OnCommentAdded{
        void onCommentAdded();
    }
}
