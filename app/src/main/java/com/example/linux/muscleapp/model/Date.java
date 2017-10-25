package com.example.linux.muscleapp.model;

import java.util.Calendar;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class has got mehotds to extract current date
 */

public class Date {
    private  int day;
    private  int month;
    private  int year;

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }
    //Get current date
    public void GetDate(){
        day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
        month = Calendar.getInstance().get(Calendar.MONTH);
        year = Calendar.getInstance().get(Calendar.YEAR);
    }
}
