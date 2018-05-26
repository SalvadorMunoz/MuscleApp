package com.example.linux.muscleapp.data.db.repositories;

import com.example.linux.muscleapp.data.db.dao.TodaySessionDao;
import com.example.linux.muscleapp.data.db.pojo.Session;

import java.util.ArrayList;

/**
 * Created by linux on 26/05/18.
 */

public class TodaySessionRepository {
    private static TodaySessionRepository instance;
    private TodaySessionDao todaySessionDao;
    private TodaySessionRepository(){

        todaySessionDao = new TodaySessionDao();
    }

    public static TodaySessionRepository getInstance(){
        if(instance == null)
            instance = new TodaySessionRepository();
        return  instance;
    }

    public ArrayList<Session> getTodaySessions(int id){
        return todaySessionDao.getTodaySessions(id);
    }


}
