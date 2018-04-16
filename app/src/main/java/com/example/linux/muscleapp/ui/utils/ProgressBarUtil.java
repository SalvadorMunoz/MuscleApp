package com.example.linux.muscleapp.ui.utils;

import android.app.ProgressDialog;
import android.content.Context;

import com.example.linux.muscleapp.MuscleAppApplication;

/**
 * Created by linux on 19/03/18.
 */

public class ProgressBarUtil {
    public  static Context context;

    public static ProgressDialog getProgressBar(){
        ProgressDialog progreso = new ProgressDialog(context);
        progreso.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progreso.setMessage("Connecting . . .");
        progreso.setCancelable(false);

        return progreso;

    }
}
