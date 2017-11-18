package com.example.linux.muscleapp.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Salvador Muñoz
 * @version 1.0
 *
 * This class is a comment
 */

public class Commentary implements Parcelable, Comparable{
    int id,idSession;
    String user,content,date;

    public Commentary(int id, int idSession, String user, String content, String date) {
        this.id = id;
        this.idSession = idSession;
        this.user = user;
        this.content = content;
        this.date=date;
    }

    protected Commentary(Parcel in) {
        id = in.readInt();
        idSession = in.readInt();
        user = in.readString();
        content = in.readString();
        date = in.readString();
    }

    public static final Creator<Commentary> CREATOR = new Creator<Commentary>() {
        @Override
        public Commentary createFromParcel(Parcel in) {
            return new Commentary(in);
        }

        @Override
        public Commentary[] newArray(int size) {
            return new Commentary[size];
        }
    };

    public int getId() {
        return id;
    }

    public int getIdSession() {
        return idSession;
    }

    public String getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public String getDate() {
        return date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(idSession);
        parcel.writeString(user);
        parcel.writeString(content);
        parcel.writeString(date);
    }

    @Override
    public int compareTo(@NonNull Object o) {
        String tmp= ((Commentary)o).getDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat thisDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        Date thisDate = null;
        try {
            date = dateFormat.parse(tmp);
            thisDate = thisDateFormat.parse(getDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.compareTo(thisDate);
    }
}
