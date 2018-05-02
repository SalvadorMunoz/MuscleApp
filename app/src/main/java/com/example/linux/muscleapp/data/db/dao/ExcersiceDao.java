package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.net.ApiAdapter;
import com.example.linux.muscleapp.net.Result;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

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
        Call<Result> call = ApiAdapter.getInstance().insertExcersices(excersice);
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
