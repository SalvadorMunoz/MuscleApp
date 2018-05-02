package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.SessionDate;
import com.example.linux.muscleapp.net.ApiAdapter;
import com.example.linux.muscleapp.net.Result;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

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
        Call<Result> call = ApiAdapter.getInstance().insertSessionDates(sessionDate);
        String message = "";
        try {
            Result result = call.execute().body();
            message = result.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String fin = message;
    }
}
