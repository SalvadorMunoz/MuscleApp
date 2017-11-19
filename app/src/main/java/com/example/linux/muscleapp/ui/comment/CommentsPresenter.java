package com.example.linux.muscleapp.ui.comment;

/**
 * Created by linux on 18/11/17.
 */

public interface CommentsPresenter {
    void fillComments(int resource);
    void addComment(int resource, String user, String message);
    void onDestroy();
}
