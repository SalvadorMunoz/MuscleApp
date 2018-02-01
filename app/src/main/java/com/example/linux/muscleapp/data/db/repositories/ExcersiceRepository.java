package com.example.linux.muscleapp.data.db.repositories;


import com.example.linux.muscleapp.data.db.dao.ExcersiceDao;
import com.example.linux.muscleapp.data.db.pojo.Excersice;

import java.util.ArrayList;

/**
 * Created by linux on 8/11/17.
 */

public class ExcersiceRepository {
    private static ExcersiceRepository instance;
    private ExcersiceDao excersiceDao;

    private ExcersiceRepository (){
        excersiceDao = new ExcersiceDao();
    }

    public static ExcersiceRepository getInstance(){
        if (instance == null)
            instance = new ExcersiceRepository();
        return instance;
    }

    public void add(Excersice excersice){
        excersiceDao.insert(excersice);
    }



    public ArrayList<Excersice> getExcersices(int sessionId){
        return excersiceDao.loadExcersices(sessionId);
    }

}
