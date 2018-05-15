package com.example.linux.muscleapp.data.db.dao;

import com.example.linux.muscleapp.data.db.pojo.Excersice;
import com.example.linux.muscleapp.data.db.pojo.Favourite;
import com.example.linux.muscleapp.net.ApiAdapter;
import com.example.linux.muscleapp.net.Result;

import java.io.IOException;

import retrofit2.Call;

/**
 * Created by linux on 13/05/18.
 */

public class FavouriteDao {
    public void insert(Favourite favourite){
        Call<Result> call = ApiAdapter.getInstance().insertFavourite(favourite);
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
