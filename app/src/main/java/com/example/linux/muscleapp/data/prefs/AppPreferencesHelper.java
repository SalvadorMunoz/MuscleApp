package com.example.linux.muscleapp.data.prefs;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.linux.muscleapp.MuscleAppApplication;

/**
 * Created by linux on 5/01/18.
 */

public class AppPreferencesHelper implements AppPreferences {
    private SharedPreferences preferences;
    private static AppPreferencesHelper appPreferencesHelper;

    private AppPreferencesHelper(){
        preferences = MuscleAppApplication.getContex().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);

    }

    public static AppPreferencesHelper newInstance(){
        if(appPreferencesHelper == null)
            appPreferencesHelper = new AppPreferencesHelper();
        return appPreferencesHelper;
    }
    public boolean getInitialize(){
        return preferences.getBoolean(PREFS_KEY_INITIALIZE,false);
    }
    public long getNumVideo(){
        return preferences.getLong(PREFS_KEY_NUM_VIDEOS,-1);
    }

    public void setInitialize(boolean value){
        preferences.edit().putBoolean(PREFS_KEY_INITIALIZE,value).commit();
    }

    public void setNumVideo(long value){
        preferences.edit().putLong(PREFS_KEY_NUM_VIDEOS,value).commit();
    }
}
