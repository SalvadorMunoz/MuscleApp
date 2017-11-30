package com.example.linux.muscleapp.ui.comment.contract;

import com.example.linux.muscleapp.data.db.pojo.Commentary;

import java.util.ArrayList;

/**
 * Created by linux on 30/11/17.
 */

public interface CommentsContract{

    interface CommentsView {
         void fillComments(ArrayList<Commentary> comments);
         void setPresenter(CommentsPresenter presenter);
    }

    interface CommentsPresenter {
        void fillComments(int resource);
        void addComment(int resource, String user, String message);
        void onDestroy();
    }
}
