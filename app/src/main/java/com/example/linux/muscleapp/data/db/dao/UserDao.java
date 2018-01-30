package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.MuscleAppApplication;
import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 22/01/18.
 */

public class UserDao {
    public ArrayList<User> loadActual(String email, String pass){
        ArrayList<User> tmp = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= MuscleappOpenHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(MuscleappContract.UserEntry.TABLE_NAME,MuscleappContract.UserEntry.ALL_COLUMNS,
                "email=? and password=?",new String[]{email,pass},null,null,null,null);
        if (cursor.moveToFirst()){
            do{
                tmp.add(new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
            }while (cursor.moveToNext());
        }


        MuscleappOpenHelper.getInstance().closeDatabase();
        return tmp;
    }
    public ArrayList<User> loadActual(String email){
        ArrayList<User> tmp = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase= MuscleappOpenHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(MuscleappContract.UserEntry.TABLE_NAME,MuscleappContract.UserEntry.ALL_COLUMNS,
                "email=?",new String[]{email},null,null,null,null);

        if (cursor.moveToFirst()){
            do{
                tmp.add(new User(cursor.getInt(0),cursor.getString(1),cursor.getString(2),
                        cursor.getString(3),cursor.getString(4),cursor.getString(5),cursor.getInt(6)));
            }while (cursor.moveToNext());
        }

        MuscleappOpenHelper.getInstance().closeDatabase();
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
