package com.example.linux.muscleapp.ui.comment;

import com.example.linux.muscleapp.data.db.pojo.Commentary;

import java.util.ArrayList;

/**
 * Created by linux on 18/11/17.
 */

public interface CommentsView {
    void fillComments(ArrayList<Commentary> comments);
    void updateComments();
}
