package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.MuscleAppApplication;
import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.User;

/**
 * Created by linux on 22/01/18.
 */

public class UserDao {
    public Cursor loadActual(String email,String pass){
        SQLiteDatabase sqLiteDatabase= MuscleappOpenHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(MuscleappContract.UserEntry.TABLE_NAME,MuscleappContract.UserEntry.ALL_COLUMNS,
                "email=? and password=?",new String[]{email,pass},null,null,null,null);
        MuscleappOpenHelper.getInstance().closeDatabase();
        return cursor;
    }
    public Cursor loadActual(String email){
        SQLiteDatabase sqLiteDatabase= MuscleappOpenHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(MuscleappContract.UserEntry.TABLE_NAME,MuscleappContract.UserEntry.ALL_COLUMNS,
                "email=?",new String[]{email},null,null,null,null);
        MuscleappOpenHelper.getInstance().closeDatabase();
        return cursor;
    }
    public void insertUser(User user){
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(MuscleappContract.UserEntry.COLUMN_EMAIL,user.getEmail());
        values.put(MuscleappContract.UserEntry.COLUMN_PASS,user.getPass());
        values.put(MuscleappContract.UserEntry.COLUMN_NAME, user.getName());
        values.put(MuscleappContract.UserEntry.COLUMN_RESIDENCE,user.getBornDate());
        values.put(MuscleappContract.UserEntry.COLUMN_BORN_DATE,user.getBornDate());
        values.put(MuscleappContract.UserEntry.COLUMN_URL,user.getUrl());

        sqLiteDatabase.insert(MuscleappContract.UserEntry.TABLE_NAME,null,values);

        MuscleappOpenHelper.getInstance().closeDatabase();
    }
}
