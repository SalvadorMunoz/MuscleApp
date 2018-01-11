package com.example.linux.muscleapp.data.db.repositories;


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
    private ArrayList<SessionDate> dates;
    private static SessionDatesRepository instance;

    private SessionDatesRepository(){
        dates = new ArrayList<>();
        initialize();
    }

    public static SessionDatesRepository getInstance(){
        if(instance == null)
            instance = new SessionDatesRepository();
        return instance;
    }
    public void add (SessionDate date){
        dates.add(date);
    }

    public void initialize(){
        add(new SessionDate(1,11,11,2017,1));
        add(new SessionDate(2,13,11,2017,1));

    }

    public ArrayList<SessionDate> getSessionDates(int sessionId){
        ArrayList<SessionDate> tmp = new ArrayList<>();

        for(int i = 0; i < dates.size();i++){
            if(sessionId == dates.get(i).getSessionId())
                tmp.add(dates.get(i));
        }

        return tmp;
    }
    public int getLastId(){
        int res=-1;
        if(dates.size()==0)
            res = 1;
        else{
            for(int i = 0;i < dates.size();i++){
                if(dates.get(i).getId() > res)
                    res = dates.get(i).getId();
            }
        }
        return res;
    }
}
