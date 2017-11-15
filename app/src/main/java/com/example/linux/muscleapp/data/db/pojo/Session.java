package com.example.linux.muscleapp.data.db.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * @author Salvador Muñoz
 * @version 1.0
 * This class is a training session
 */

public class Session implements Parcelable{
    int id,url;
    String name, creationDate, creator;
    ArrayList<String> trainingDates;
    ArrayList<Excersice> excersices;

    public Session(int id, int url, String name, String creationDate, String creator, ArrayList<String> trainingDates, ArrayList<Excersice> excersices) {
        this.id = id;
        this.url = url;
        this.name = name;
        this.creationDate = creationDate;
        this.creator = creator;
        this.trainingDates = trainingDates;
        this.excersices = excersices;
    }

    protected Session(Parcel in) {
        id = in.readInt();
        url = in.readInt();
        name = in.readString();
        creationDate = in.readString();
        creator = in.readString();
        trainingDates = in.createStringArrayList();
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

    public String getName() {
        return name;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public String getCreator() {
        return creator;
    }

    public ArrayList<String> getTrainingDates() {
        return trainingDates;
    }

    public ArrayList<Excersice> getExcersices() {
        return excersices;
    }

    public int getUrl() {
        return url;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeInt(url);
        parcel.writeString(name);
        parcel.writeString(creationDate);
        parcel.writeString(creator);
        parcel.writeStringList(trainingDates);
    }
}