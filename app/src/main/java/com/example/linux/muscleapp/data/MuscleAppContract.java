package com.example.linux.muscleapp.data;

import android.provider.BaseColumns;

/**
 * Created by linux on 19/05/18.
 */

public class MuscleAppContract {
    private MuscleAppContract(){
    }
   public static final String DATABASE_NAME ="muscleapp.db";
       public static final int DATABASE_VERSION=10;

         public  static class UserEntry implements BaseColumns {
         public static final String TABLE_NAME ="user";
         public static final String COLUMN_EMAIL = "email";
         public static final String COLUMN_NAME="name";

         public static final String COLUMN_RESIDENCE="residence";
         public static final String COLUMN_BORN_DATE="bornDate";

         public static final String [] ALL_COLUMNS= new String []{
                          BaseColumns._ID,COLUMN_EMAIL,COLUMN_NAME,COLUMN_RESIDENCE,COLUMN_BORN_DATE
         };

         public static final String SQL_CREATE_ENTRIES= String.format("CREATE TABLE %s" +
               "(%s INTEGER PRIMARY KEY AUTOINCREMENT," +
               " %s TEXT NOT NULL," +
               " %s TEXT NOT NULL," +
               " %s TEXT NOT NULL," +
               " %s TEXT NOT NULL)", TABLE_NAME,BaseColumns._ID,COLUMN_EMAIL,COLUMN_NAME
               ,COLUMN_RESIDENCE,COLUMN_BORN_DATE);

         public static final String SQL_DELETE_ENTRIES=String.format("DROP TABLE IF EXISTS %s", TABLE_NAME);

         public static final String SQL_INSERT_ENTRY=String.format("INSERT INTO %s" +
                         "(%s,%s,%s,%s) VALUES ('%s','%s','%s','%s')",
                        TABLE_NAME,COLUMN_EMAIL,COLUMN_NAME,COLUMN_RESIDENCE,COLUMN_BORN_DATE,
                        "salvador.munoz.mendez@gmail.com","a","a","02-02-1994");
    }

}
