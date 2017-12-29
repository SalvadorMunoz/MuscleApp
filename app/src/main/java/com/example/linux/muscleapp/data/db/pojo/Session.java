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

public class Session implements Parcelable,Comparable{
    int id,user,urlImage;
    String name, pass,creationDate;

    public Session(int id, int user, int urlImage, String name, String pass, String creationDate) {
        this.id = id;
        this.user = user;
        this.urlImage = urlImage;
        this.name = name;
        this.pass = pass;
        this.creationDate = creationDate;
    }

    protected Session(Parcel in) {
        id = in.readInt();
        user = in.readInt();
        urlImage = in.readInt();
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

    @Override
    public int compareTo(@NonNull Object o) {
        String tmp= ((Session)o).getCreationDate();
        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        DateFormat thisDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = null;
        Date thisDate = null;
        try {
            date = dateFormat.parse(tmp);
            thisDate = thisDateFormat.parse(getCreationDate());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date.compareTo(thisDate);
    }

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

    public int getUrlImage() {
        return urlImage;
    }

    public void setUrlImage(int urlImage) {
        this.urlImage = urlImage;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(user);
        parcel.writeInt(urlImage);
        parcel.writeString(name);
        parcel.writeString(pass);
        parcel.writeString(creationDate);
    }
}
