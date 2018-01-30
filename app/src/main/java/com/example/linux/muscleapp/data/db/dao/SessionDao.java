package com.example.linux.muscleapp.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.Session;

import java.util.ArrayList;

/**
 * Created by linux on 30/01/18.
 */

public class SessionDao {
    public ArrayList<Session> loadAll(){
        ArrayList<Session> tmp = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(MuscleappContract.SessionEntry.TABLE_NAME,MuscleappContract.SessionEntry.ALL_COLUMNS,null,null,null,null,MuscleappContract.SessionEntry.COLUMN_CREATION_DATE+" DESC",null);

        if(cursor.moveToFirst()){
            do {
                tmp.add(new Session(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4),cursor.getString(5)));
            }while (cursor.moveToNext());
        }

        MuscleappOpenHelper.getInstance().closeDatabase();
        return tmp;
    }
}
