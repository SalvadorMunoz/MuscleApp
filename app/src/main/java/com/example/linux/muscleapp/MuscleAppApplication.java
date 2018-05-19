package com.example.linux.muscleapp;

import android.app.Application;

import com.example.linux.muscleapp.data.db.MuscleAppOpenHelper;
import com.example.linux.muscleapp.data.prefs.AppPreferencesHelper;

/**
 * Created by linux on 5/01/18.
 */

public class MuscleAppApplication extends Application {
    private AppPreferencesHelper appPreferencesHelper;
    private static MuscleAppApplication contex;
    private MuscleAppOpenHelper muscleappOpenHelper;

    public MuscleAppApplication() {
        contex = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appPreferencesHelper=AppPreferencesHelper.newInstance();
        muscleappOpenHelper = MuscleAppOpenHelper.getInstance();
        muscleappOpenHelper.openDatabase();

    }
    public static MuscleAppApplication getContex(){
        return contex;
    }
    public AppPreferencesHelper getAppPreferencesHelper(){
        return appPreferencesHelper;
    }
}
