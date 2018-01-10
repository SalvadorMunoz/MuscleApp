package com.example.linux.muscleapp.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class has got mehotds to extract current date
 */

public class SessionDate implements Parcelable{
    private int id;
    private  int day;
    private  int month;
    private  int year;
    private int sessionId;

    public SessionDate(int id,int day, int month, int year,int sessionId) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.sessionId = sessionId;
    }


    protected SessionDate(Parcel in) {
        id = in.readInt();
        day = in.readInt();
        month = in.readInt();
        year = in.readInt();
        sessionId = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(id);
        dest.writeInt(day);
        dest.writeInt(month);
        dest.writeInt(year);
        dest.writeInt(sessionId);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public void setSessionId(int sessionId) {
        this.sessionId = sessionId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
