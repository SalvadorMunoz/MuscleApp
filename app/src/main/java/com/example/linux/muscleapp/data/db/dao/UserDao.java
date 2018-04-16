package com.example.linux.muscleapp.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.User;
import com.example.linux.muscleapp.net.ApiAdapter;
import com.example.linux.muscleapp.net.Result;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;

/**
 * Created by linux on 22/01/18.
 */

public class UserDao {
    ArrayList<User> tmp ;




    public ArrayList<User> loadActual(String email, String password){
        tmp = new ArrayList<>();
        User current = new User(0,email,password,"","","",0,"");
        Call<Result> call = ApiAdapter.getInstance().getUsers(current,email);

        try {
            Result result = call.execute().body();
            tmp = result.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;

    }
    public ArrayList<User> loadActual(String email){
        tmp = new ArrayList<>();
        Call<Result> call = ApiAdapter.getInstance().getCurrentUser(email);

        try {
            Result result = call.execute().body();
            tmp = result.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return tmp;

    }

    public String LoadNameFromId( int id){
        String tmp = "";
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(MuscleappContract.UserEntry.TABLE_NAME, new String[]{MuscleappContract.UserEntry.COLUMN_NAME},"_id=?",new String[]{String.valueOf(id)},null,null,null,null);

        if(cursor.moveToFirst()){
            do{
                tmp = cursor.getString(0);
            }while (cursor.moveToNext());
        }

        MuscleappOpenHelper.getInstance().closeDatabase();
        return tmp;
    }
    public void insertUser(User user){
        Call<Result> call = ApiAdapter.getInstance().insertUser(user);
        String message = "";
        try {
            Result result = call.execute().body();
            message = result.getMessage();

        } catch (IOException e) {
            e.printStackTrace();
        }

        String fin = message;

    }

    public void sendConfirmEmail(String email){
        Call<Result> call = ApiAdapter.getInstance().sendConfirm(email);
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
