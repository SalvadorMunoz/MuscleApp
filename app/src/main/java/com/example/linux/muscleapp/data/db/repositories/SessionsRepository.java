package com.example.linux.muscleapp.data.db.repositories;

import com.example.linux.muscleapp.R;
import com.example.linux.muscleapp.data.db.dao.SessionDao;
import com.example.linux.muscleapp.data.db.pojo.Session;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class represents a database table session
 */

public class SessionsRepository {
    private ArrayList<Session> sessions;
    private SessionDao sessionDao;

    private static SessionsRepository instace;

    private SessionsRepository() {
        sessionDao = new SessionDao();
        sessions = new ArrayList<>();
    }

    public static SessionsRepository getInstace(){
        if(instace == null)
            instace = new SessionsRepository();
        return  instace;
    }


    public void add (Session session){
        sessions.add(session);
    }

    public ArrayList<Session> getSessions(){
        return sessionDao.loadAll();
    }
    public int getLastId(){
        int res=-1;
        if(sessions.size()==0)
            res = 1;
        else{
            for(int i = 0;i < sessions.size();i++){
                if(sessions.get(i).getId() > res)
                    res = sessions.get(i).getId();
            }
        }
        return res;
    }
}
