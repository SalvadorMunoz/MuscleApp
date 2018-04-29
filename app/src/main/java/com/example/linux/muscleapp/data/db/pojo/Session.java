package com.example.linux.muscleapp.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @author Salvador Muñoz
 * @version 1.0
 * This class is a training session
 */

public class Session implements Parcelable,Comparable<Session>{
    int id,user;
    String name, pass, creationDate;

    public Session(int id, int user, String name, String pass, String creationDate) {
        this.id = id;
        this.user = user;
        this.name = name;
        this.pass = pass;
        this.creationDate = creationDate;
    }


    protected Session(Parcel in) {
        id = in.readInt();
        user = in.readInt();
        name = in.readString();
        pass = in.readString();
        creationDate = in.readString();
    }

    public static final Creator<Session> CREATOR = new Creator<Session>() {
        @Override
        public Session createFromParcel(Parcel in) {
            return new Session(in);
        }

        @Override
        public Session[] newArray(int size) {
            return new Session[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(user);
        parcel.writeString(name);
        parcel.writeString(pass);
        parcel.writeString(creationDate);
    }

    @Override
    public int compareTo(@NonNull Session session) {
        return 0;
    }
}
