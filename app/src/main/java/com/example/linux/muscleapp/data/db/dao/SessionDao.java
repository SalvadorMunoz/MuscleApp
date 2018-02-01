package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
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

    public void insert(Session session){
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();
        ContentValues values = new ContentValues();

        values.put(MuscleappContract.SessionEntry.COLUMN_USER_ID,session.getUser());
        values.put(MuscleappContract.SessionEntry.COLUMN_URL,session.getUrlImage());
        values.put(MuscleappContract.SessionEntry.COLUMN_NAME,session.getName());
        values.put(MuscleappContract.SessionEntry.COLUMN_PASS,session.getPass());
        values.put(MuscleappContract.SessionEntry.COLUMN_CREATION_DATE,session.getCreationDate());

        sqLiteDatabase.insert(MuscleappContract.SessionEntry.TABLE_NAME,null,values);

        MuscleappOpenHelper.getInstance().closeDatabase();
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
