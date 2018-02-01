package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;

import java.util.ArrayList;

/**
 * Created by linux on 1/02/18.
 */

public class SessionDateDao {
    public ArrayList<SessionDate> loadSessionDates(int session){
        ArrayList<SessionDate> sessionDates = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();


        Cursor cursor = sqLiteDatabase.query(MuscleappContract.SessionDatesEntry.TABLE_NAME,MuscleappContract.SessionDatesEntry.ALL_COLUMNS,MuscleappContract.SessionDatesEntry.COLUMN_SESSION+"=?",
                new String[]{String.valueOf(session)},null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                sessionDates.add(new SessionDate(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getInt(3),cursor.getInt(4)));
            }while (cursor.moveToNext());
        }
        MuscleappOpenHelper.getInstance().closeDatabase();


        return sessionDates;
    }

    public void insert(SessionDate sessionDate){
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(MuscleappContract.SessionDatesEntry.COLUMN_DAY,sessionDate.getDay());
        values.put(MuscleappContract.SessionDatesEntry.COLUMN_MONTH,sessionDate.getMonth());
        values.put(MuscleappContract.SessionDatesEntry.COLUMN_YEAR,sessionDate.getYear());
        values.put(MuscleappContract.SessionDatesEntry.COLUMN_SESSION,sessionDate.getSessionId());


        sqLiteDatabase.insert(MuscleappContract.SessionDatesEntry.TABLE_NAME,null,values);

        MuscleappOpenHelper.getInstance().closeDatabase();
    }
}
