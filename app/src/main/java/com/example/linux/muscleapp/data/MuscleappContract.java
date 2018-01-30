package com.example.linux.muscleapp.data;

import android.provider.BaseColumns;

/**
 * Created by linux on 22/01/18.
 */

public final class MuscleappContract {
    private MuscleappContract(){

    }

    public static final String DATABASE_NAME ="muscleapp.db";
    public static final int DATABASE_VERSION=3;

    public  static class UserEntry implements BaseColumns{
        public static final String TABLE_NAME ="user";
        public static final String COLUMN_EMAIL = "email";
        public static final String COLUMN_NAME="name";
        public static final String COLUMN_PASS="password";
        public static final String COLUMN_RESIDENCE="residence";
        public static final String COLUMN_BORN_DATE="bornDate";
        public static final String COLUMN_URL="url";

        public static final String [] ALL_COLUMNS= new String []{
          BaseColumns._ID,COLUMN_EMAIL,COLUMN_NAME,COLUMN_PASS,COLUMN_RESIDENCE,COLUMN_BORN_DATE,COLUMN_URL
        };

        public static final String SQL_CREATE_ENTRIES= String.format("CREATE TABLE %s" +
                "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL," +
                " %s TEXT NOT NULL)", TABLE_NAME,BaseColumns._ID,COLUMN_EMAIL,COLUMN_NAME,COLUMN_PASS
        ,COLUMN_RESIDENCE,COLUMN_BORN_DATE,COLUMN_URL);

        public static final String SQL_DELETE_ENTRIES=String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

        public static final String SQL_INSERT_ENTRY=String.format("INSERT INTO %s" +
                "(%s,%s,%s,%s,%s,%s) VALUES ('%s','%s','%s','%s','%s','%s')",
                TABLE_NAME,COLUMN_EMAIL,COLUMN_NAME,COLUMN_PASS,COLUMN_RESIDENCE,COLUMN_BORN_DATE,COLUMN_URL,
                "yo@yo.com","yo","yo","asd","02-02-1998","");
    }

    public static class SessionEntry implements BaseColumns{
        public static final String TABLE_NAME="session";
        public static final String COLUMN_USER_ID="userId";
        public static final String COLUMN_URL ="url";
        public static final String COLUMN_NAME = "name";
        public static final String COLUMN_PASS ="password";
        public static final String COLUMN_CREATION_DATE="creationDate";

        public static final String [] ALL_COLUMNS = new String []{
          BaseColumns._ID,COLUMN_USER_ID,COLUMN_URL,COLUMN_NAME,COLUMN_PASS,COLUMN_CREATION_DATE
        };

        public static final String SQL_CREATE_ENTRIES = String.format("CREATE TABLE %s" +
                "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                " %s INTEGER REFERENCES %s(%s) ON DELETE RESTRICT ON UPDATE CASCADE," +
                " %s INTEGER," +
                " %s TEXT NOT NULL," +
                " %s TEXT," +
                " %s TEXT NOT NULL)", TABLE_NAME,BaseColumns._ID,COLUMN_USER_ID, UserEntry.TABLE_NAME,
                UserEntry._ID, COLUMN_URL, COLUMN_NAME,COLUMN_PASS,COLUMN_CREATION_DATE);

        public static final String SQL_DELETE_ENTRIES=String.format("DROP TABLE IF EXISTS %s",TABLE_NAME);
        public static final String SQL_INSERT_ENTRY=String.format("INSERT INTO %s" +
                "(%s,%s,%s,%s,%s) VALUES (%s,%s,'%s','%s','%s')", TABLE_NAME,COLUMN_USER_ID,COLUMN_URL,COLUMN_NAME,COLUMN_PASS,COLUMN_CREATION_DATE,
                1,1,"Asdfghjk","","2018-01-01 03:03:23");
    }
}
