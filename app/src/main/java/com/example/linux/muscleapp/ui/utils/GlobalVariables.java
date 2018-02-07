package com.example.linux.muscleapp.ui.utils;

/**
 * Created by linux on 11/01/18.
 */

public class GlobalVariables {
    public static final int OPEN_SEE = 0;
    public static final  int OPEN_ADD = 1;
    public static final int OPEN_SETTINGS=2;
    public static final int  OPEN_ABOUTUS= 3;
    public static boolean fromAboutUs = false;



    public static void setFromAboutUs() {
        fromAboutUs = true;
    }
}
