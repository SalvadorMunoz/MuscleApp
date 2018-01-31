package com.example.linux.muscleapp.data.db.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleappContract;
import com.example.linux.muscleapp.data.db.MuscleappOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.Commentary;

import java.util.ArrayList;

/**
 * Created by linux on 31/01/18.
 */

public class CommentaryDao {

    public ArrayList<Commentary> loadAllComentaries(int session){
        ArrayList <Commentary> tmp = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(MuscleappContract.CommentaryEntry.TABLE_NAME,MuscleappContract.CommentaryEntry.ALL_COLUMNS,"session=?",new String[]{String.valueOf(session)},null,null,MuscleappContract.CommentaryEntry.COLUMN_DATE+" ASC",null);
        if(cursor.moveToFirst()){
            do{
                tmp.add(new Commentary(cursor.getInt(0),cursor.getInt(1),cursor.getInt(2),cursor.getString(3),cursor.getString(4)));
            }while (cursor.moveToNext());
        }

        MuscleappOpenHelper.getInstance().closeDatabase();
        return tmp;
    }

    public String getNameFromId(int id){
        String name="";
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();

        Cursor cursor = sqLiteDatabase.query(MuscleappContract.UserEntry.TABLE_NAME,new String[]{MuscleappContract.UserEntry.COLUMN_NAME},"_id=?",new String[]{String.valueOf(id)},null,null,null,null);
        if(cursor.moveToFirst()){
            do{
                name = cursor.getString(0);
            }while (cursor.moveToNext());
        }

        MuscleappOpenHelper.getInstance().closeDatabase();
        return name;
    }

    public void insert(Commentary commentary){
        SQLiteDatabase sqLiteDatabase = MuscleappOpenHelper.getInstance().openDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MuscleappContract.CommentaryEntry.COLUMN_SESSION,commentary.getIdSession());
        contentValues.put(MuscleappContract.CommentaryEntry.COLUMN_USER,commentary.getUser());
        contentValues.put(MuscleappContract.CommentaryEntry.COLUMN_CONTENT,commentary.getContent());
        contentValues.put(MuscleappContract.CommentaryEntry.COLUMN_DATE,commentary.getDate());

        sqLiteDatabase.insert(MuscleappContract.CommentaryEntry.TABLE_NAME,null,contentValues);

        MuscleappOpenHelper.getInstance().closeDatabase();
    }
}
