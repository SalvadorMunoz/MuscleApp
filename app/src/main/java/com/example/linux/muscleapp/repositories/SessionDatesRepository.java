package com.example.linux.muscleapp.repositories;

import com.example.linux.muscleapp.pojo.SessionDate;

import java.util.ArrayList;

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
        add(new SessionDate(11,11,2017,1));
        add(new SessionDate(13,11,2017,1));

    }

    public ArrayList<SessionDate> getSessionDates(){
        return dates;
    }
}
