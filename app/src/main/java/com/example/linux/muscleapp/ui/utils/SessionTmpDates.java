package com.example.linux.muscleapp.ui.utils;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;

import java.util.ArrayList;
import java.util.Date;

/**
 * Created by linux on 2/12/17.
 */

public class SessionTmpDates {
    public static final int EXCERSICES_LIMIT = 10;
    private static ArrayList<SessionDate> dates;
    private static ArrayList<Excersice>  excersices;
    private static Session toDeleteOfFavourites;
    private static int pos;

    public static void initialize(){
        if(dates == null && excersices == null) {
            dates = new ArrayList<>();
            excersices = new ArrayList<>();
        }
    }

    public static void  addDate(SessionDate date){
        dates.add(date);
    }

    public static void  addExcersice(Excersice excersice){
        if(excersices.size() < EXCERSICES_LIMIT)
            excersices.add(excersice);
    }

    public static ArrayList<SessionDate> getDates() {
        return dates;
    }

    public static ArrayList<Excersice> getExcersices() {
        return excersices;
    }
    public static  void destroyDates(){
        dates = null;
        excersices = null;
    }
    public static void setUrlVideo(int id, String url){
        for(int i = 0; i < excersices.size();i++){
            if(excersices.get(i).getId() == id)
                excersices.get(i).setUrl(url);
        }
    }
    public static void clearDates(){
        dates.clear();
    }
    public static void deleteDate (int position){
        dates.remove(position);
    }
    public static void deleteExcersice (int position){
        excersices.remove(position);
    }

    public static Session getToDeleteOfFavourites() {
        return toDeleteOfFavourites;
    }

    public static void setToDeleteOfFavourites(Session toDeleteOfFavourites) {
        SessionTmpDates.toDeleteOfFavourites = toDeleteOfFavourites;
    }

    public static int getPos() {
        return pos;
    }

    public static void setPos(int pos) {
        SessionTmpDates.pos = pos;
    }
}
