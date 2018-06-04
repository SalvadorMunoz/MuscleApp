package com.example.linux.muscleapp.data.db.repositories;

import com.example.linux.muscleapp.data.db.dao.SessionDao;
import com.example.linux.muscleapp.data.db.pojo.Session;

import java.util.ArrayList;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class represents a database table session
 */

public class SessionsRepository {
    private SessionDao sessionDao;

    private static SessionsRepository instace;

    private SessionsRepository() {
        sessionDao = new SessionDao();
    }

    public static SessionsRepository getInstace(){
        if(instace == null)
            instace = new SessionsRepository();
        return  instace;
    }


    public void add (Session session){
        sessionDao.insert(session);
    }

    public ArrayList<Session> getSessions(){
        return sessionDao.loadAll();
    }

    public int getIdFromSession(Session session){
        return sessionDao.getIdFomSession(session);
    }

    public ArrayList<Session> getUsersSession(int id){
        return sessionDao.getUsersSession(id);
    }


}
