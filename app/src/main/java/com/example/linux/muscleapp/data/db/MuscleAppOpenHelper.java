package com.example.linux.muscleapp.data.db;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;

import com.example.linux.muscleapp.MuscleAppApplication;
import com.example.linux.muscleapp.data.MuscleAppContract;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by linux on 19/05/18.
 */

public class MuscleAppOpenHelper extends SQLiteOpenHelper {
    private SQLiteDatabase sqLiteDatabase;
    private volatile static MuscleAppOpenHelper instance;
    private AtomicInteger openCounter = new AtomicInteger();

    public synchronized  static  MuscleAppOpenHelper getInstance(){
         if(instance == null)
            instance = new MuscleAppOpenHelper();
        return instance;
    }
    private MuscleAppOpenHelper(){
        super(MuscleAppApplication.getContex(), MuscleAppContract.DATABASE_NAME, null, MuscleAppContract.DATABASE_VERSION);
    }

   @Override
   public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MuscleAppContract.UserEntry.SQL_CREATE_ENTRIES);
        Log.e("crear usuarios",MuscleAppContract.UserEntry.SQL_CREATE_ENTRIES);
        sqLiteDatabase.execSQL(MuscleAppContract.UserEntry.SQL_INSERT_ENTRY);
        Log.e("llenar usuarios",MuscleAppContract.UserEntry.SQL_INSERT_ENTRY);
   }
   @Override
   public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
       Log.e("borrar usuarios",MuscleAppContract.UserEntry.SQL_DELETE_ENTRIES);

       sqLiteDatabase.execSQL(MuscleAppContract.UserEntry.SQL_DELETE_ENTRIES);
        Log.e("borrar usuarios",MuscleAppContract.UserEntry.SQL_DELETE_ENTRIES);
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
