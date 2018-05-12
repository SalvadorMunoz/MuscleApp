package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.Commentary;
import com.example.linux.muscleapp.net.ApiAdapter;
import com.example.linux.muscleapp.net.Result;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by linux on 31/01/18.
 */

public class CommentaryDao {
    private ArrayList <Commentary> tmp;
    public ArrayList<Commentary> loadAllComentaries(int session){
        tmp = new ArrayList<>();
        Call<Result> call = ApiAdapter.getInstance().getCommentaries(session);
        String message = "";
        try {
            Result result = call.execute().body();
            message = result.getMessage();
            tmp = result.getCommentaries();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String fin = message;


        return tmp;
    }



    public void insert(Commentary commentary){
        Call<Result> call = ApiAdapter.getInstance().insertCommentary(commentary);
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
