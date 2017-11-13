package com.example.linux.muscleapp.data.db.repositories;


import com.example.linux.muscleapp.data.db.pojo.Excersice;

import java.util.ArrayList;

/**
 * Created by linux on 8/11/17.
 */

public class ExcersiceRepository {
    private ArrayList<Excersice> excersices;
    private static ExcersiceRepository instance;

    private ExcersiceRepository (){
        excersices = new ArrayList<>();
        initialize();
    }

    public static ExcersiceRepository getInstance(){
        if (instance == null)
            instance = new ExcersiceRepository();
        return instance;
    }

    public void add(Excersice excersice){
        excersices.add(excersice);
    }

    private void initialize(){
        add(new Excersice(1,1, "Biceps","Biceps","","",2,4,0));
    }

    public ArrayList<Excersice> getExcersices(){
        return excersices;
    }
}
