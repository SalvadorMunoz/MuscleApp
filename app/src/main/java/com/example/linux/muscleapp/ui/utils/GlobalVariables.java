package com.example.linux.muscleapp.ui.utils;

/**
 * Created by linux on 11/01/18.
 */

public class GlobalVariables {
    public static final int OPEN_SEE = 0;
    public static final  int OPEN_ADD = 1;
    public static final int OPEN_SETTINGS=2;
    public static final int  OPEN_ABOUTUS= 3;
    public static final int OPEN_FAVOURITES=4;
    public static final int OPEN_TODAY=5;
    public static final int OPEN_SEARCH_USER=6;
    public static final  int OPEN_PROFILE = 7;
    public static boolean fromAboutUs = false;
    public static String imgPath;
    public static boolean fromSelectImage = false;


    public static boolean fromMain = true;

    public static boolean  fromMyProfile=false;


    public static void setFromAboutUs(boolean value) {
        fromAboutUs = value;
    }
}
