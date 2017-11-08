package com.example.linux.muscleapp.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class has got mehotds to extract current date
 */

public class SessionDate implements Parcelable{
    private  int day;
    private  int month;
    private  int year;
    private int sessionId;

    public SessionDate(int day, int month, int year,int sessionId) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.sessionId = sessionId;
    }

    protected SessionDate(Parcel in) {
        day = in.readInt();
        month = in.readInt();
        year = in.readInt();
        sessionId = in.readInt();
    }

    public static final Creator<SessionDate> CREATOR = new Creator<SessionDate>() {
        @Override
        public SessionDate createFromParcel(Parcel in) {
            return new SessionDate(in);
        }

        @Override
        public SessionDate[] newArray(int size) {
            return new SessionDate[size];
        }
    };

    public int getSessionId() {
        return sessionId;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(day);
        parcel.writeInt(month);
        parcel.writeInt(year);
        parcel.writeInt(sessionId);
    }
}
