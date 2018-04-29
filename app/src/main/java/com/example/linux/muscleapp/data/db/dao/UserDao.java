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
    ArrayList<String> usernames;

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

    public ArrayList<String> LoadNameFromId(){
        int id = 0;
        usernames = new ArrayList<>();
        Call<Result> call = ApiAdapter.getInstance().getUserName(id);

        try {
            Result result = call.execute().body();
            tmp = result.getUsers();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0;i< tmp.size();i++){
            usernames.add(tmp.get(i).getName());
        }

        return usernames;
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

    public void sendRecoveryEmail(String email){
        Call<Result> call = ApiAdapter.getInstance().sendRecovery(email);
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
