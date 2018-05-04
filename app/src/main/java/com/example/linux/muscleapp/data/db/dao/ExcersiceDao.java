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
    private ArrayList<Excersice> tmp;

    public ArrayList<Excersice> loadExcersices(int session){
        tmp = new ArrayList<>();
        Call<Result> call = ApiAdapter.getInstance().getExcersices(session);

        try {
            Result result = call.execute().body();
            tmp = result.getExcersices();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
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
