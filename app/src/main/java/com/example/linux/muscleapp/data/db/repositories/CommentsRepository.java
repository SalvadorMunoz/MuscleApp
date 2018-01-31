package com.example.linux.muscleapp.data.db.repositories;


import com.example.linux.muscleapp.data.db.dao.CommentaryDao;
import com.example.linux.muscleapp.data.db.pojo.Commentary;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by linux on 7/11/17.
 */

public class CommentsRepository {
    private ArrayList<Commentary> comments;
    private static CommentsRepository instance;
    private CommentaryDao commentaryDao;

    private CommentsRepository(){
        comments = new ArrayList<>();
        commentaryDao =new CommentaryDao();
    }


    public static CommentsRepository getInstace() {
        if(instance == null)
            instance = new CommentsRepository();
        return instance;
    }


    public void add(Commentary comment){
        commentaryDao.insert(comment);
    }

    public ArrayList<Commentary> getComments(int idSession){

        return  commentaryDao.loadAllComentaries(idSession);
    }
    public String getNameFromId(int user){
        return commentaryDao.getNameFromId(user);
    }

}
