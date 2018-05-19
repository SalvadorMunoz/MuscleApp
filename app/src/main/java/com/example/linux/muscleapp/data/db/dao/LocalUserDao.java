package com.example.linux.muscleapp.data.db.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.linux.muscleapp.data.MuscleAppContract;
import com.example.linux.muscleapp.data.db.MuscleAppOpenHelper;
import com.example.linux.muscleapp.data.db.pojo.User;

import java.util.ArrayList;

/**
 * Created by linux on 19/05/18.
 */

public class LocalUserDao {
    public ArrayList<User> loadCurrent(String email) {
        ArrayList<User> tmp = new ArrayList<>();
        SQLiteDatabase sqLiteDatabase = MuscleAppOpenHelper.getInstance().openDatabase();
        Cursor cursor = sqLiteDatabase.query(MuscleAppContract.UserEntry.TABLE_NAME, MuscleAppContract.UserEntry.ALL_COLUMNS, "email=?",new String[]{email} , null, null,null);

        if (cursor.moveToFirst()) {
            do {
                tmp.add(new User(cursor.getInt(0), cursor.getString(1), "", cursor.getString(2), cursor.getString(3), cursor.getString(4),0,""));
            } while (cursor.moveToNext());

        }
        MuscleAppOpenHelper.getInstance().closeDatabase();
        return tmp;

    }
}
