package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;

import java.util.ArrayList;

/**
 * Created by linux on 1/02/18.
 */

public class ExcersiceDao {

    public ArrayList<Excersice> loadExcersices(int session){
        ArrayList<Excersice>excersices = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(MuscleappContract.ExcersiceEntry.TABLE_NAME,MuscleappContract.ExcersiceEntry.ALL_COLUMNS,MuscleappContract.ExcersiceEntry.COLUMN_SESSION+"=?",
                new String[]{String.valueOf(session)},null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                excersices.add(new Excersice(cursor.getInt(0),cursor.getInt(1),cursor.getString(2),cursor.getString(3),cursor.getString(4),
                        cursor.getString(5),cursor.getInt(6),cursor.getInt(7),cursor.getInt(8)));
            }while (cursor.moveToNext());
        }
        MuscleappOpenHelper.getInstance().closeDatabase();
        return excersices;
    }

    public void insert(Excersice excersice){
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(MuscleappContract.ExcersiceEntry.COLUMN_SESSION,excersice.getSession());
        values.put(MuscleappContract.ExcersiceEntry.COLUMN_NAME,excersice.getName());
        values.put(MuscleappContract.ExcersiceEntry.COLUMN_MUSCLE,excersice.getMuscle());
        values.put(MuscleappContract.ExcersiceEntry.COLUMN_URL,excersice.getUrl());
        values.put(MuscleappContract.ExcersiceEntry.COLUMN_TYPETIME,excersice.getTypeTime());
        values.put(MuscleappContract.ExcersiceEntry.COLUMN_SERIES,excersice.getSeries());
        values.put(MuscleappContract.ExcersiceEntry.COLUMN_REPETITIONS,excersice.getRepetitions());
        values.put(MuscleappContract.ExcersiceEntry.COLUMN_TIME,excersice.getTime());

        sqLiteDatabase.insert(MuscleappContract.ExcersiceEntry.TABLE_NAME,null,values);

        MuscleappOpenHelper.getInstance().closeDatabase();
    }
}
