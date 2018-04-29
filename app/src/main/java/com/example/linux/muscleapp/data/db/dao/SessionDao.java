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
        int res=0;
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(MuscleappContract.SessionEntry.TABLE_NAME,new String[]{MuscleappContract.SessionEntry._ID},
                MuscleappContract.SessionEntry.COLUMN_NAME+"=? and "+MuscleappContract.SessionEntry.COLUMN_USER_ID+"=? and "+MuscleappContract.SessionEntry.COLUMN_CREATION_DATE+
                        "=?",new String []{session.getName(),String.valueOf(session.getUser()),session.getCreationDate()},null,null,null);

        if(cursor.moveToFirst()){
            do{
                res = cursor.getInt(0);
            }while (cursor.moveToNext());
        }

        MuscleappOpenHelper.getInstance().closeDatabase();
        return res;
    }

}
