package com.example.linux.muscleapp.data.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.linux.muscleapp.MuscleAppApplication;
import com.example.linux.muscleapp.data.MuscleappContract;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by linux on 22/01/18.
 */

public class MuscleappOpenHelper extends SQLiteOpenHelper{
    private  SQLiteDatabase sqLiteDatabase;
    private volatile static MuscleappOpenHelper instance;
    private AtomicInteger openCounter = new AtomicInteger();

    public synchronized  static  MuscleappOpenHelper getInstance(){
        if(instance == null)
            instance = new MuscleappOpenHelper();
        return instance;
    }

    private MuscleappOpenHelper(){
        super(MuscleAppApplication.getContex(), MuscleappContract.DATABASE_NAME, null, MuscleappContract.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(MuscleappContract.UserEntry.SQL_CREATE_ENTRIES);
        Log.e("crear usuarios",MuscleappContract.UserEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(MuscleappContract.SessionEntry.SQL_CREATE_ENTRIES);
        Log.e("crear sesiones",MuscleappContract.SessionEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(MuscleappContract.CommentaryEntry.SQL_CREATE_ENTRIES);
        Log.e("crear comentarios",MuscleappContract.CommentaryEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(MuscleappContract.ExcersiceEntry.SQL_CREATE_ENTRIES);
        Log.e("crear ejercicios",MuscleappContract.ExcersiceEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(MuscleappContract.SessionDatesEntry.SQL_CREATE_ENTRIES);
        Log.e("crear fechas",MuscleappContract.SessionDatesEntry.SQL_CREATE_ENTRIES);

        sqLiteDatabase.execSQL(MuscleappContract.UserEntry.SQL_INSERT_ENTRY);
        Log.e("llenar usuarios",MuscleappContract.UserEntry.SQL_INSERT_ENTRY);
        sqLiteDatabase.execSQL(MuscleappContract.SessionEntry.SQL_INSERT_ENTRY);;
        Log.e("llenar sesiones",MuscleappContract.SessionEntry.SQL_INSERT_ENTRY);
        sqLiteDatabase.execSQL(MuscleappContract.CommentaryEntry.SQL_INSERT_ENTRY);;
        Log.e("llenar comentarios",MuscleappContract.CommentaryEntry.SQL_INSERT_ENTRY);
        sqLiteDatabase.execSQL(MuscleappContract.ExcersiceEntry.SQL_INSERT_ENTRY);;
        Log.e("llenar ejercicios",MuscleappContract.ExcersiceEntry.SQL_INSERT_ENTRY);
        sqLiteDatabase.execSQL(MuscleappContract.SessionEntry.SQL_INSERT_ENTRY);;
        Log.e("llenar fechas",MuscleappContract.SessionDatesEntry.SQL_INSERT_ENTRY);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(MuscleappContract.SessionDatesEntry.SQL_DELETE_ENTRIES);
        Log.e("borrar fechas",MuscleappContract.SessionDatesEntry.SQL_DELETE_ENTRIES);


        sqLiteDatabase.execSQL(MuscleappContract.ExcersiceEntry.SQL_DELETE_ENTRIES);
        Log.e("borrar ejercicios",MuscleappContract.ExcersiceEntry.SQL_DELETE_ENTRIES);

        sqLiteDatabase.execSQL(MuscleappContract.CommentaryEntry.SQL_DELETE_ENTRIES);
        Log.e("borrar comentarios",MuscleappContract.CommentaryEntry.SQL_DELETE_ENTRIES);


        sqLiteDatabase.execSQL(MuscleappContract.SessionEntry.SQL_DELETE_ENTRIES);
        Log.e("borrar sesiones",MuscleappContract.SessionEntry.SQL_DELETE_ENTRIES);


        sqLiteDatabase.execSQL(MuscleappContract.UserEntry.SQL_DELETE_ENTRIES);
        Log.e("borrar usuarios",MuscleappContract.UserEntry.SQL_DELETE_ENTRIES);
        onCreate(sqLiteDatabase);

    }

    public synchronized SQLiteDatabase openDatabase(){
        if(openCounter.incrementAndGet() == 1)
            sqLiteDatabase = getWritableDatabase();
        return sqLiteDatabase;
    }

    public synchronized void closeDatabase(){
        if(openCounter.decrementAndGet() ==0 )
            sqLiteDatabase.close();
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        if(!db.isReadOnly()){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                db.setForeignKeyConstraintsEnabled(true);
            else
                db.execSQL("PRAGMA foreign_keys=1");
        }
    }
}
