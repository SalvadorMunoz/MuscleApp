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
    private ArrayList<SessionDate> tmp;
    public ArrayList<SessionDate> loadSessionDates(int session){
        tmp = new ArrayList<>();
        Call<Result> call = ApiAdapter.getInstance().getSessionDates(session);

        try {
            Result result = call.execute().body();
            tmp = result.getSessionDates();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;
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
