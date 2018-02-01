package com.example.linux.muscleapp.data.db.repositories;


import com.example.linux.muscleapp.data.db.dao.SessionDateDao;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is a date of sessions repository
 */

public class SessionDatesRepository {
    private SessionDateDao sessionDateDao;
    private static SessionDatesRepository instance;

    private SessionDatesRepository(){
        sessionDateDao = new SessionDateDao();
    }

    public static SessionDatesRepository getInstance(){
        if(instance == null)
            instance = new SessionDatesRepository();
        return instance;
    }
    public void add (SessionDate date){
        sessionDateDao.insert(date);
    }



    public ArrayList<SessionDate> getSessionDates(int sessionId){

        return sessionDateDao.loadSessionDates(sessionId);
    }

}
