package com.example.linux.muscleapp.ui.comment.interactor;

import com.example.linux.muscleapp.data.db.pojo.Commentary;

import java.util.ArrayList;

/**
 * Created by linux on 18/11/17.
 */

public interface CommentsInteractor {
    void fillComments(int resource);
    void addComment(int resource,int user,String message);
    interface OnLoadFinish{
        void onLoadFinish(ArrayList<Commentary> comments);
    }
}
