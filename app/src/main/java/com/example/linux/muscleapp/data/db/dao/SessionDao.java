package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.net.ApiAdapter;
import com.example.linux.muscleapp.net.Result;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by linux on 30/01/18.
 */

public class SessionDao {
    ArrayList<Session> tmp ;

    public ArrayList<Session> loadAll(){
        tmp = new ArrayList<>();
        Call<Result> call = ApiAdapter.getInstance().getSessions();
        try {
            Result result = call.execute().body();
            tmp = result.getSessions();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmp;

    }

    public void insert(Session session){
        Call<Result> call = ApiAdapter.getInstance().insertSession(session);
        String message = "";
        try {
            Result result = call.execute().body();
            message = result.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String fin = message;
    }

    public int getIdFomSession(Session session){
        tmp = new ArrayList<>();
        Call<Result> call = ApiAdapter.getInstance().getSessionId(session);
        try {
            Result result = call.execute().body();
            tmp = result.getSessions();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return tmp.get(0).getId();

    }

}
