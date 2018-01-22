package com.example.linux.muscleapp.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.MuscleAppApplication;
import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;

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
}
