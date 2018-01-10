package com.example.linux.muscleapp.data.db.repositories;


import com.example.linux.muscleapp.data.db.pojo.Commentary;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by linux on 7/11/17.
 */

public class CommentsRepository {
    private ArrayList<Commentary> comments;
    private static CommentsRepository instance;

    private CommentsRepository(){
        comments = new ArrayList<>();
        initialize();
    }



    private void initialize(){
        add(new Commentary(1,1,"Indurain","Me mola tio","04-11-2017"));
        add(new Commentary(2,1,"Cristiano Ronaldo","Suuuuuu!","05-11-2017"));
        add(new Commentary(3,1,"Cristiano Ronaldo","Eres un barato","05-11-2017"));
        add(new Commentary(4,1,"Indurain","Habla con mi abogado","06-11-2017"));
        add(new Commentary(5,2,"Cristiano","Hola barato","04-11-2017"));
    }

    public void add(Commentary comment){
        comments.add(comment);
    }

    public ArrayList<Commentary> getComments(int idSession){
        ArrayList<Commentary> res = new ArrayList<>();
        for (int i = 0; i <comments.size();i++){
            if(comments.get(i).getIdSession() == idSession)
                res.add(comments.get(i));
        }
        Collections.sort(res);
        return  res;
    }


    public int getSize(int idSession){
        return getComments(idSession).size();
    }

    public static CommentsRepository getInstace() {
        if(instance == null)
            instance = new CommentsRepository();
        return instance;
    }
    public int getLastId(){
        int res=-1;
        if(comments.size()==0)
            res = 1;
        else{
            for(int i = 0;i < comments.size();i++){
                if(comments.get(i).getId() > res)
                    res = comments.get(i).getId();
            }
        }
        return res;
    }
}
