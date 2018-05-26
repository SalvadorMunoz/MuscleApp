package com.example.linux.muscleapp.data.db.dao;

import com.example.linux.muscleapp.data.db.pojo.Session;
import com.example.linux.muscleapp.net.ApiAdapter;
import com.example.linux.muscleapp.net.Result;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by linux on 26/05/18.
 */

public class TodaySessionDao {
    ArrayList<Session> tmp ;

    public ArrayList<Session> getTodaySessions(int id){
        tmp = new ArrayList<>();
        Call<Result> call = ApiAdapter.getInstance().getTodaySessions(id);

        try {
            Result result = call.execute().body();
            tmp = result.getSessions();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;

    }
}
